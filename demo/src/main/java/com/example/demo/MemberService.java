package com.example.demo;

import com.example.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final List<Member> members=new ArrayList<>();

    public void join(Member member) {
        members.add(member);
    }

    public Member findByName(String name) {
        for (Member member : members) {
            if(member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }
}
