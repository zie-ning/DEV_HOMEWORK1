package com.example.homework1.service;

import com.example.homework1.domain.Member;
import com.example.homework1.util.LoginException;
import com.example.homework1.util.NotPresentException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
public class MemberService {

    private static HashMap<Integer,Member> members=new HashMap<>();
    private static Integer sequence=0;

    public Member join(Member member) {
        member.setId(++sequence);
        members.put(sequence,member);
        return member;
    }

    public Member login(String account, String password) {
        for (Integer id: members.keySet()) {
            Member member = members.get(id);
            if(member.getAccount().equals(account) && member.getPassword().equals(password)) {
                return member;
            }
        }
        throw new NotPresentException("존재하지 않는 계정 혹은 비밀번호입니다.");
    }

    public Member findMember(Integer accountId){
        if(!members.containsKey(accountId)) throw new LoginException("존재하지 않는 id입니다.");
        return members.get(accountId);
    }

    public void updateMember(Integer accountId, String password, String email) {
        Member member = findMember(accountId);
        if(password!=null) member.setPassword(password);
        if(email!=null) member.setEmail(email);
        member.setUpdateDate(LocalDate.now());
    }

    public void removeMember(Integer accountId) {
        members.remove(accountId);
    }
}
