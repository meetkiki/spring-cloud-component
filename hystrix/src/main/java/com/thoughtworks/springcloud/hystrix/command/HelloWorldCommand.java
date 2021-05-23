package com.thoughtworks.springcloud.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Objects;

public class HelloWorldCommand extends HystrixCommand<String> {

    private final String name;

    // 指定一个HystrixCommandGroupKey，这样熔断策略会按照此组执行
    public HelloWorldCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("myGroup")));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if (Objects.isNull(name)){
            throw new IllegalArgumentException();
        }
        return "hello " + name;
    }

    @Override
    protected String getFallback() {
        // super . No fallback available.
        return "this is fallback msg";
    }
}
