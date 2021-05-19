package com.thoughtworks.springcloud.feign.encoder;


import com.thoughtworks.springcloud.feign.domain.Person;
import com.thoughtworks.springcloud.feign.factory.FeignClientFactory;
import org.junit.jupiter.api.Test;

public class CustomerEncoder {

    EncoderClient client = FeignClientFactory.create(EncoderClient.class, new MyEncoder());
//    EncoderClient client = FeignClientFactory.create(EncoderClient.class);


    @Test
    public void encoderDemo7() {
        try {
            client.encoderDemo7(new Person());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
