package com.lion.youranmok.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lion.youranmok.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TokenController {
    private final KakaoService kakaoService;

    @GetMapping("/auth/login")
    public String getList() {
        return "/auth/kakao/callback";
        }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws JsonProcessingException {
        kakaoService.kakaoToken(code);
        return "redirect:/category/home";
    }
}
