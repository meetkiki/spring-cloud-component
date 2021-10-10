package com.thoughtworks.mini.springboot.web;

import com.thoughtworks.mini.springboot.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletRegistration;
import java.util.HashSet;

public class MiniSpringBootApplication {


    public static void run() {
        int port = 18088;
        String contextPath = "/mini";

        Tomcat tomcat = new Tomcat();
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        tomcat.setPort(port);

        try {
            Context context = tomcat.addContext(contextPath, baseDir);

            //ServletContextInitializer
            context.addServletContainerInitializer((c, servletContext) -> {
                ServletRegistration.Dynamic helloServlet = servletContext.addServlet("ServletName", new HelloServlet());
                helloServlet.addMapping("/hello");
            }, new HashSet<>());

            // 启动tomcat
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        // 挂起tomcat
        tomcat.getServer().await();
    }

}
