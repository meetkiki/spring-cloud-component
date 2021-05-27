package com.thoughtworks.springcloud.hystrix.controller;


import com.thoughtworks.springcloud.hystrix.clients.FeignClientTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    private final FeignClientTest feignClientTest;

    public TestController(FeignClientTest feignClientTest) {
        this.feignClientTest = feignClientTest;
    }

    @GetMapping
    public String test(@RequestParam String name) {
        log.info("test === " + name);
        String demo1 = feignClientTest.getDemo1(name);
        log.info("result == " + demo1);
        return "success:" + demo1;
    }

}
