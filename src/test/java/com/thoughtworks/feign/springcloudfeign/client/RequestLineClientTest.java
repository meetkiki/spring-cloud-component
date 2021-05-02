package com.thoughtworks.feign.springcloudfeign.client;

import com.thoughtworks.feign.springcloudfeign.factory.FeignClientFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestLineClientTest {

    RequestLineClient client = FeignClientFactory.create(RequestLineClient.class);

    @Test
    public void testRequestLine() {
        // Headers 空格不敏感
        client.testRequestLine("tao", "auth");
    }

    @Test
    public void testRequestLine2() {
        // RequestLine 空格不敏感
        client.testRequestLine2("tao2");
    }


    @Test
    public void testRequestLine3() {
        // 使用Map一次传多个请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tao3");
        map.put("age", Arrays.asList(16, 18, 20));
        client.testRequestLine3(map);
    }

    @Test
    public void testRequestLine4() {
        client.testRequestLine4("tao4");
    }


    @Test()
    public void testRequestLine5() {
        // 默认情况 HttpURLConnection 判断body不为空则用POST请求
        client.testRequestLine5("tao4");
    }

    @Test()
    public void testRequestLine8() {
        client.testRequestLine8("tao4", 18);
    }


    @Test()
    public void testRequestLine9() {
        // 使用Map一次传多个请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("auth", "1123112312");
        client.testRequestLine9("tao", map);
    }

}
