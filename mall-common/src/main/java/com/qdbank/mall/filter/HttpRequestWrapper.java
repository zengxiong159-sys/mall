package com.qdbank.mall.filter;

import com.qdbank.mall.util.ContextHolder;
import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author Qdccb
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private String body;

    // 所有参数的Map集合
    private Map<String, String[]> parameterMap;

    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8.name());
        this.parameterMap = new HashMap<>();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }


    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream bis = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        return new MyServletInputStream(bis);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    @Override
    public String getParameter(String name) {
        String[] v = parameterMap.get(name);
        return v == null ? null : v[0];
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Boolean requestFlag = ContextHolder.getRequestFlag();
        if (null != requestFlag) {
            Vector<String> vector = new Vector<>(parameterMap.keySet());
            return vector.elements();
        }else {
            return super.getParameterNames();
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        Boolean requestFlag = ContextHolder.getRequestFlag();
        if (null != requestFlag) {
            return parameterMap.get(name);
        }else {
            return super.getParameterValues(name);
        }
    }

    static class MyServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream bis;

        public MyServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return bis.read();
        }
    }
}