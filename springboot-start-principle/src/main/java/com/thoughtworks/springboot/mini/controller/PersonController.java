package com.thoughtworks.springboot.mini.controller;

import com.thoughtworks.springboot.mini.entity.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {

    @RequestMapping("/getUser")
    public Person getPerson(@RequestBody Person person) {
        return person;
    }

    @RequestMapping("/returnJson")
    public Map<String, String> returnJson() {
        Map<String, String> retMap = new HashMap<>();
        retMap.put("name", "张三");
        return retMap;
    }
}
