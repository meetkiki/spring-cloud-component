package com.thoughtworks.springcloud.feign.domain;


public class Person {

    private String name = "tao";
    private Integer age = 18;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
