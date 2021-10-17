package com.thoughtworks.springboot.mini.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {

    private final ApplicationContext context;

    public ThreadPoolConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public ThreadPoolExecutor myConfigThreadPool() {
        return new ThreadPoolExecutor(100, 100,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }

}
