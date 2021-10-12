package com.thoughtworks.mini.springboot.web;

import com.thoughtworks.mini.springboot.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletRegistration;

public class MiniSpringBootApplication {


    public static void run() throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("target"); // 工作目录

        Connector connector = new Connector();
        connector.setPort(8080); // 端口号
        tomcat.getService().addConnector(connector);


        Context context = tomcat.addContext("/", null);

        // ServletContextInitializer
        context.addServletContainerInitializer((c, servletContext) -> {
            ServletRegistration.Dynamic helloServlet = servletContext.addServlet("ServletName", new HelloServlet());
            helloServlet.addMapping("/hello");
        }, null);


        tomcat.start();  // 启动tomcat
        tomcat.getServer().await(); // 挂起tomcat
    }

}
