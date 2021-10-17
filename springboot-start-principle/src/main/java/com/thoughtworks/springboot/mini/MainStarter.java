package com.thoughtworks.springboot.mini;


import com.thoughtworks.springboot.auto.configuration.annotation.AutoConfiguration;
import com.thoughtworks.springboot.mini.web.MiniSpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.thoughtworks.springboot.mini.config")
@AutoConfiguration
public class MainStarter {

    public static void main(String[] args) throws Exception {
        MiniSpringBootApplication.run(MainStarter.class);
    }

}
