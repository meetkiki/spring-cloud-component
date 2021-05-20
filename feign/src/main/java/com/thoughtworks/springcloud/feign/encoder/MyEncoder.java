package com.thoughtworks.springcloud.feign.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class MyEncoder implements Encoder {

    private static final JsonMapper MAPPER = new JsonMapper();

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (object != null) {
            try {
                template.body(MAPPER.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}

