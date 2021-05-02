package com.thoughtworks.feign.springcloudfeign.encoder;


import com.thoughtworks.feign.springcloudfeign.domain.Person;
import com.thoughtworks.feign.springcloudfeign.factory.FeignClientFactory;
import org.junit.jupiter.api.Test;

public class CustomerEncoder {

    EncoderClient client = FeignClientFactory.create(EncoderClient.class,new MyEncoder());


    @Test
    void encoderDemo7() {
        try {
            client.encoderDemo7(new Person());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
