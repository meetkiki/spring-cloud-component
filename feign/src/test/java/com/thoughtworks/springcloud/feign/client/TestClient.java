package com.thoughtworks.springcloud.feign.client;

import com.thoughtworks.springcloud.feign.FeignApplication;
import feign.Feign;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClient {


    @Test
    public void test() {
        DemoClient client = Feign.builder().target(DemoClient.class, "http://localhost:8080");
        String result = client.getDemo1("tao");
        System.out.println(result);
    }


    @Test
    public void testClientOnSpringBoot() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FeignApplication.class);
        DemoClient client = context.getBean(DemoClient.class);
        String result = client.getDemo1("tao");
        System.out.println(result);
    }


}
