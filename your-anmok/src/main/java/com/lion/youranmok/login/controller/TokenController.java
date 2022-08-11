package com.lion.youranmok.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TokenController {

    @GetMapping("/login")
    public String getList() {
        return "LoginForm";
        }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody
    String kakaoCallback() {
        return "카카오 인증 완료";
    }
}
