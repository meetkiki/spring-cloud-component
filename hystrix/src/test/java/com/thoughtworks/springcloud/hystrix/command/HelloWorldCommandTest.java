package com.thoughtworks.springcloud.hystrix.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class HelloWorldCommandTest {

    @Test
    void runNormal() {
        // 1、普通方式
        String s = new HelloWorldCommand("Tao").execute();
        Assertions.assertEquals("hello Tao", s);
    }

    @Test
    void runNormalWhenFallBack() {
        String fallbackValue = new HelloWorldCommand(null).execute();
        // 说明：若你没有提供fallback函数，那结果是：
        // com.netflix.hystrix.exception.HystrixRuntimeException: HelloWorldCommand failed and no fallback available.
        // "this is fallback msg"
        Assertions.assertEquals("this is fallback msg", fallbackValue);
    }


    @Test
    void runAsync() throws ExecutionException, InterruptedException {
        // 2、异步方式。什么时候需要时候什么时候get
        Future<String> s = new HelloWorldCommand("Tao").queue();

        Assertions.assertEquals("hello Tao", s.get());
    }


    @Test
    void runRxJava() {
        // 3、RxJava方式。吞吐量更高，但对程序员的要求更高

        Observable<String> s = new HelloWorldCommand("Tao").observe();
        s.subscribe(result -> Assertions.assertEquals("hello Tao", result));
    }


}