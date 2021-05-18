package com.thoughtworks.feign.springcloudfeign.config;


import feign.Request;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("ALL")
public class FeignConfig {

    @Bean
    public Request.Options options() {
        return new Request.Options(1000, 3000);
    }

}
