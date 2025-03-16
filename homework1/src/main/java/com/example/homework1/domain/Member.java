package com.example.homework1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class Member {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String email;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Member(String account, String password, String name, String email) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
        createDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return id+"번 회원\n계정: "+account+"\n이메일: "+email+"\n가입일: "+createDate;
    }
}
