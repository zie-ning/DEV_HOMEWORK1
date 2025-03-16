package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
public class Member {

    private String id;
    private String name;
    private String authorization; //USER, ADMIN

    public Member(String name, String authorization) {
        id=UUID.randomUUID().toString().substring(0,6);
        this.name = name;
        this.authorization = authorization;
    }
}
