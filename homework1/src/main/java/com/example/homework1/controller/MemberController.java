package com.example.homework1.controller;

import com.example.homework1.domain.Member;
import com.example.homework1.util.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.homework1.service.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody Member member) {
        Member joinMember = memberService.join(member);
        return joinMember.getName()+"님 회원가입을 축하드립니다. accountId로 "+joinMember.getId()+"번을 부여받았습니다.";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody LoginDto dto, HttpServletRequest request) {
        if(request.getSession(false)!=null){
            throw new LoginException("이미 로그인이 되어 있습니다.");
        }
        Member loginMember = memberService.login(dto.getAccount(), dto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        return loginMember.getName()+"님이 로그인을 하였습니다.";
    }

    @GetMapping("/signout")
    public String signout(HttpServletRequest request) {
        if(request.getSession(false)==null){
            throw new LoginException("로그인이 되어 있지 않습니다.");
        }
        request.getSession(false).invalidate(); //세션 삭제
        return "로그아웃을 하였습니다.";
    }

    @GetMapping("/detail")
    public String printAccount(@RequestParam("accountId") Integer accountId) {
        Member member = memberService.findMember(accountId);
        return member.toString();
    }

    @PostMapping("/edit")
    public String editMember(@RequestParam("accountId") Integer accountId, @RequestBody EditMemberDto request){
        memberService.updateMember(accountId,request.getPassword(),request.getEmail());
        return "회원 정보가 성공적으로 변경되었습니다.";
    }

    @GetMapping("/remove")
    public String removeMember(@RequestParam("accountId") Integer accountId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate(); //로그아웃 처리
        }
        memberService.removeMember(accountId);
        return "회원 탈퇴가 완료되었습니다.";
    }

    @Data
    static class LoginDto{
        String account;
        String password;
    }

    @Data
    static class EditMemberDto{
        String password;
        String email;
    }
}
