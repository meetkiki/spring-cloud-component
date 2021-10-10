package com.thoughtworks.mini.springboot.web;

import com.thoughtworks.mini.springboot.config.MiniSpringbootConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 在tomcat源码中:
 * org.apache.catalina.startup.ContextConfig#lifecycleEvent(org.apache.catalina.LifecycleEvent
 * org.apache.catalina.startup.ContextConfig#configureStart
 * org.apache.catalina.startup.ContextConfig#webConfig
 * org.apache.catalina.startup.ContextConfig#processServletContainerInitializers
 * //通过spi的机制加载 classpath下ServletContainerInitializer
 * WebappServiceLoader<ServletContainerInitializer> loader = new WebappServiceLoader<>(context);
 * detectedScis = loader.load(ServletContainerInitializer.class);
 */
public class MiniSpringBootWebApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("初始化MiniWebApplicationInitializer...");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MiniSpringbootConfig.class);

        context.setServletContext(servletContext);

        context.refresh();


        //注册dispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
