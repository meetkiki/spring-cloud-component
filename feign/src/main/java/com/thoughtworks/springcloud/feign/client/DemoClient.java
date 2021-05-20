package com.thoughtworks.springcloud.feign.client;

import feign.Contract;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@FeignClient(name = "demo", url = "http://localhost:8080", configuration = DemoClient.Config.class)
public interface DemoClient {

    @RequestLine("GET /feign/demo1?name={name}")
    String getDemo1(@Param("name") String name);

    class Config {
        @Bean
        public Contract contract() {
            return new Contract.Default();
        }
    }

}

