package com.thoughtworks.springcloud.feign.template;

import feign.CollectionFormat;
import feign.template.QueryTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QueryTemplateTest {


    /**
     * 通常用来处理 @QueryMap
     * QueryTemplate
     */
    @Test
    public void testMethodName() {
        // 可以看到key也是可以使用模版的。当然你也可以直接使用字符串即可，也可以混合使用
        QueryTemplate template = QueryTemplate.create("hobby-{arg}", Arrays.asList("basket", "foot"), StandardCharsets.UTF_8);
        // 对于模板里的变量，可以最终会替换成对应的值 如果没有就为空
        Map<String, Object> params = new HashMap<>();
        // params.put("arg", "1");

        String result = template.expand(params);

        Assertions.assertEquals("hobby-%7Barg%7D=basket&hobby-%7Barg%7D=foot", result);

        template = QueryTemplate.create("grade", Arrays.asList("1", "2"), StandardCharsets.UTF_8, CollectionFormat.CSV);

        Assertions.assertEquals("grade=1%2C2", template.toString());

    }

}
