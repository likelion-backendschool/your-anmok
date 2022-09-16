package com.lion.youranmok.user.controller;

import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/modify")
    public ResponseEntity changeNickname(@RequestBody KakaoUserDto dto) {

        userService.changeNickname(dto);

        return new ResponseEntity(HttpStatus.OK);

    }


}
