package com.example.demo.aop;

import com.example.demo.AuthController;
import com.example.demo.MemberService;
import com.example.demo.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Aspect //어드바이저
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final MemberService memberService;

    @Before("@annotation(com.example.demo.aop.Authorization)")
    public void checkAuthorization(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if(arg instanceof AuthController.MemberAccessDto){
                AuthController.MemberAccessDto request = (AuthController.MemberAccessDto) arg;
                Member findMember = memberService.findByName(request.getName());
                if(!findMember.getAuthorization().equalsIgnoreCase("ADMIN")){
                    throw new AuthException("ADMIN 이외의 등급은 접근할 수 없습니다.");
                }
            }
        }
    }
}
