package com.example.demo;

import com.example.demo.aop.Authorization;
import com.example.demo.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @Authorization
    @PostMapping("/post/write")
    public String writePost(@RequestBody MemberAccessDto request) {
        return "게시글을 작성할 수 있습니다.";
    }

    @Authorization
    @PostMapping("/post/comment/write/")
    public String writeComment(@RequestBody MemberAccessDto request) {
        return "댓글을 작성할 수 있습니다.";
    }

    @Data
    public static class MemberAccessDto{
        private String name;
    }

    @PostMapping("/member/save")
    public String saveMember(@RequestBody MemberSaveDto request) {
        Member member = new Member(request.getName(), request.getAuthorization());
        memberService.join(member);
        return member.getName()+"님 회원가입을 축하합니다!";
    }
    @Data
    public static class MemberSaveDto {
        private String name;
        private String authorization;
    }
}
