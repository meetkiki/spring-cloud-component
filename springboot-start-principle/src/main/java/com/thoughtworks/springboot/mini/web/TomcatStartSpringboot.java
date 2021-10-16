package com.thoughtworks.springboot.mini.web;

import com.thoughtworks.springboot.mini.config.MiniSpringbootConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;

//public class TomcatStartSpringboot extends SpringBootServletInitializer {
public class TomcatStartSpringboot{

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MiniSpringbootConfig.class);
    }

}
