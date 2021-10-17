package com.thoughtworks.springboot.auto.configuration.config;


import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnClassPath;
import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnMissBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@ConditionalOnClassPath(ThreadPoolExecutor.class)
@ConditionalOnMissBean(ThreadPoolExecutor.class)
public class AutoThreadPoolConfiguration {

    @Bean
    public ThreadPoolExecutor autoConfigThreadPool() {
        return new ThreadPoolExecutor(10, 10,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }


}
