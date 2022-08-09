package com.lion.youranmok.gathering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/gathering")
public class GatheringListController {

    @GetMapping("/list")
    public String getList() {

        return "gathering/list";
    }
}

//6 - 제 작업
//develop - 머지하고 싶은 브랜치
//7 - 아름님 작업
//
//6 -> develop에 커밋 했음 push X
//하고싶은 것: 7->develop (merge)

// 7을 내가 풀 받아서

// 깃허브에 올라온 아름님 브랜치를 develop에 머지 (목표)
// feautre/#7-popup

