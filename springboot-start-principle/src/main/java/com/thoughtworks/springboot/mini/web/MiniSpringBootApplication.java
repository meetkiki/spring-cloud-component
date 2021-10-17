package com.thoughtworks.springboot.mini.web;

import com.thoughtworks.springboot.mini.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.servlet.ServletRegistration;

public class MiniSpringBootApplication {


    public static void miniRun(Class<?> mainClz) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("target"); // 工作目录

        Connector connector = new Connector();
        connector.setPort(8080); // 端口号
        tomcat.getService().addConnector(connector);


        Context context = tomcat.addContext("/", null);

        // ServletContextInitializer
        context.addServletContainerInitializer((c, servletContext) -> {
            AnnotationConfigServletWebApplicationContext applicationContext = new AnnotationConfigServletWebApplicationContext();
            applicationContext.register(mainClz);
            applicationContext.setServletContext(servletContext);
            applicationContext.refresh();


            ServletRegistration.Dynamic helloServlet = servletContext.addServlet("ServletName", new HelloServlet());
            helloServlet.addMapping("/hello");
        }, null);

        final ErrorPage errorPage = new ErrorPage();
        errorPage.setErrorCode(404);
        errorPage.setLocation("/error");
        context.addErrorPage(errorPage);


        tomcat.start();  // 启动tomcat
        tomcat.getServer().await(); // 挂起tomcat
    }

}
