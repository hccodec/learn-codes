package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class RequestLoginBean {
    private String type = "RequestLoginBean";

    public String getName(String name) {
        return name + "_" + type;
    }
}