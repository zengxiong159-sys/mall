package com.qdbank.mall.config;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NacosConfig
 * @Description  注册中心外置tomcat部署需要手动加入监听
 * @Author ningyuehuai
 * @Date 2022/2/23 15:53
 * @Version 1.0
 **/
@Component
@Slf4j
public class NacosConfig implements ApplicationRunner {
    @Autowired(required = false)
    private NacosAutoServiceRegistration registration;
    @Value("${server.port}")
    Integer port;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (registration != null && port != null) {
            //如果getTomcatPort()端口获取异常,就采用配置文件中配置的端口
            Integer tomcatPort = port;
            try {
                tomcatPort = new Integer(getTomcatPort());
            } catch (Exception e) {
                e.printStackTrace();
            }
            registration.setPort(tomcatPort);
            registration.start();
        }
    }

    /**
     * 获取外置tomcat端口
     */
    public String getTomcatPort() throws Exception {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        Iterator<ObjectName> iterator = objectNames.iterator();
        String tomcatport = port+"";
        while (iterator.hasNext()){
            ObjectName objectName = iterator.next();
            tomcatport = objectName.getKeyProperty("port");
        }
        log.info("获取外置tomcat端口:{}",port);
        return tomcatport;
    }
}
