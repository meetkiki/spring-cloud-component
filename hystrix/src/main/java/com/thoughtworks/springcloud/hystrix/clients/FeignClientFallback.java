package com.thoughtworks.springcloud.hystrix.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class FeignClientFallback implements FeignClientTest {

    @Override
    @ResponseBody
    public String getDemo1(String name) {
        return "this fallback msg.";
    }

}
