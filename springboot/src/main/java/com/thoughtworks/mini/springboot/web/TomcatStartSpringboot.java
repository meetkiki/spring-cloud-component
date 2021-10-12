package com.thoughtworks.mini.springboot.web;

import com.thoughtworks.mini.springboot.config.MiniSpringbootConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//public class TomcatStartSpringboot extends SpringBootServletInitializer {
public class TomcatStartSpringboot{

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MiniSpringbootConfig.class);
    }

}
