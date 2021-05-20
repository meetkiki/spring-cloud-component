package com.thoughtworks.springcloud.feign.template;

import feign.template.BodyTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BodyTemplateTest {

    /**
     * BodyTemplate 处理@Body注解的
     *
     * 单独对 %7B开头 和 %7D结尾的做了处理
     *
     * 转化为{   }
     */
    @Test
    public void testMethodName() {
        BodyTemplate template = BodyTemplate.create("data:{body}");

        Map<String, Object> params = new HashMap<>();
        params.put("body", "{\"name\": \"tao\",\"age\": 18}");

        String result = template.expand(params);

        Assertions.assertEquals("data:{\"name\": \"tao\",\"age\": 18}", result);
    }

}
