package com.thoughtworks.feign.springcloudfeign.template;

import feign.template.UriTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class UriTemplateTest {

    @Test
    public void testUriTemplate1() {
        UriTemplate template = UriTemplate.create("http://example.com/{foo}", UTF_8);
        Map<String, Object> params = new HashMap<>();
        params.put("foo", "bar");
        String result = template.expand(params);
        Assertions.assertEquals("http://example.com/bar", result);
    }


    @Test
    public void testUriTemplate2() {
        UriTemplate template = UriTemplate.create("http://example.com/{empty}{foo}index.html{frag}", false, UTF_8);
        Map<String, Object> params = new HashMap<>();
//        params.put("empty", null);
        // 对斜杠不转义
        params.put("foo", "houses/");
        params.put("frag", "?g=sec1.2");
        String result = template.expand(params);
        Assertions.assertEquals("http://example.com/houses/index.html%3Fg%3Dsec1.2", result);
    }


}
