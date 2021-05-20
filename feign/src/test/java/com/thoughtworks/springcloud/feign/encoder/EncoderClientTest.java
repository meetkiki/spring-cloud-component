package com.thoughtworks.springcloud.feign.encoder;

import com.thoughtworks.springcloud.feign.domain.Person;
import com.thoughtworks.springcloud.feign.factory.FeignClientFactory;
import org.junit.jupiter.api.Test;

class EncoderClientTest {

    EncoderClient client = FeignClientFactory.create(EncoderClient.class);

    @Test
    void encoderDemo1() {
        client.encoderDemo1("tao1", 18);
    }

    @Test
    void encoderDemo2() {
        try {
            client.encoderDemo2("tao2", 18);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo3() {
        try {
            client.encoderDemo3("tao3", 18);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo4() {
        try {
            client.encoderDemo4("tao4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo5() {
        try {
            client.encoderDemo5("tao5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo6() {
        try {
            client.encoderDemo6(new Person());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo7() {
        try {
            client.encoderDemo7(new Person());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void encoderDemo8() {
        try {
            client.encoderDemo8(new Person());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}