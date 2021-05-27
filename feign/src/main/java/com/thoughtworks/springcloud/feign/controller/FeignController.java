package com.thoughtworks.springcloud.feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {

    @GetMapping("/demo1")
    public String getDemo1(@RequestParam String name, @RequestHeader(value = "auth", required = false) String auth) {
        log.info("name === " + name);
        log.info("auth === " + auth);
        return "success:" + name;
    }


    @PostMapping("/demo1")
    public String postDemo1(@RequestBody String body, @RequestHeader(value = "auth", required = false) String auth) {


        log.info("body === " + body);
        log.info("auth === " + auth);
        return "success body:" + body;
    }
}
