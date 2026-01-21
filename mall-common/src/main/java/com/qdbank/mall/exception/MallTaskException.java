package com.qdbank.mall.exception;

/**
 * @ClassName MallTaskException
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/24 15:22
 * @Version 1.0
 **/
public class MallTaskException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public MallTaskException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MallTaskException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MallTaskException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public MallTaskException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
