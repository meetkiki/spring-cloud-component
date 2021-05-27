package com.thoughtworks.springcloud.hystrix.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "feignClientTest", url = "http://localhost:8090/feign", fallback = FeignClientFallback.class)
public interface FeignClientTest {

    @GetMapping("/demo1")
    String getDemo1(String name);

}
