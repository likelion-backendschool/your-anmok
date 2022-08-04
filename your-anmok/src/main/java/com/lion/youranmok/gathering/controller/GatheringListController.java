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
