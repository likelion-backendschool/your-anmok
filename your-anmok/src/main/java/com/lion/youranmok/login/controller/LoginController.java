package com.lion.youranmok.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/hi")
    public String getList() {

        return "login/hi";
    }
}
