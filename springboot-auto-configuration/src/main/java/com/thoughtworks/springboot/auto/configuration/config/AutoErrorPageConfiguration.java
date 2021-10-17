package com.thoughtworks.springboot.auto.configuration.config;


import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnBeanPresent;
import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnClassPath;
import com.thoughtworks.springboot.auto.configuration.servlet.ErrorServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 自动装配一个error servlet页面
 * <p>
 * 1. 在spring.factories中配置自动装配
 * 2. 在spring-autoconfigure-metadata.properties中配置条件
 * （为什么有了3还需要2,因为自动装配阶段，所有类并不需要全部解析其注解上的值，相反如果只是配置 可以加快启动的速度）
 * 3. 在类上加验证条件（可选，严格意义上可以防止ComponentScan扫描到）
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnClassPath(Servlet.class)
@ConditionalOnBeanPresent(ServletContext.class)
public class AutoErrorPageConfiguration {

    private final ApplicationContext applicationContext;
    private final ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void registerErrorPage() {
        // 注册一个error的Page
        ServletContext servletContext = (ServletContext) applicationContext.getBean("servletContext");

        ServletRegistration.Dynamic errorPage = servletContext.addServlet("errorPage", errorServlet());

        errorPage.addMapping("/error");
    }

    @Bean
    public ErrorServlet errorServlet() {
        return new ErrorServlet(threadPoolExecutor);
    }


}
