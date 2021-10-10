package com.meetkiki.other;

import lombok.Value;

import java.util.UUID;

@Value
public class IdService {

    public String generateId() {
        return UUID.randomUUID().toString();
    }

}
