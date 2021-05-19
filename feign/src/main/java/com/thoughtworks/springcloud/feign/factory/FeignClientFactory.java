package com.thoughtworks.springcloud.feign.factory;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.Encoder;

public final class FeignClientFactory {

    public static <T> T create(Class<T> clz) {
        return Feign.builder()
                /**
                 * feign.Logger.NoOpLogger 什么都不输出，它是Feign的默认使用的Logger实现，也就是不会给控制台输出
                 * feign.Logger.JavaLogger 使用 feign.Logger.JavaLogger输出，但是日志级别的FINE级别，默认不会输出到控制台
                 * feign.Logger.ErrorLogger 错误输出。使用的System.err.printf()
                 */
                .logger(new Logger.ErrorLogger()).logLevel(Logger.Level.FULL)
                // 关闭重试
                .retryer(Retryer.NEVER_RETRY)
                // 把404也解码 -> 这样就不会以异常形式抛出，中断程序
                .decode404()
                .target(clz, "http://localhost:8080");
    }

    public static <T> T create(Class<T> clz, Encoder encoder) {
        return Feign.builder()
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .encoder(encoder)
                .retryer(Retryer.NEVER_RETRY)
                .decode404()
                .target(clz, "http://localhost:8080");
    }

}
