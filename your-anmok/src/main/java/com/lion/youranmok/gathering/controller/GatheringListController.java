package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.category.dto.CategoryNameDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gathering")
public class GatheringListController {

    @GetMapping("/list")
    public String getList(Model model) {
        GatheringListDto gatheringListDto = new GatheringListDto();
        List<GatheringListDetailDto> gatheringList = new ArrayList<>();
        List<CategoryNameDto> categoryList = new ArrayList<>();

        //번개모임 정보 세팅
        for(int i = 0; i < 50; i++) {
            GatheringListDetailDto gatheringListDetailDto = new GatheringListDetailDto();
            gatheringListDetailDto.setBoardId(i+1);
            gatheringListDetailDto.setCurCnt(5);
            gatheringListDetailDto.setMeetAt(LocalDateTime.now());
            gatheringListDetailDto.setStore("스타벅스 상도"+(i+1)+"호점");
            gatheringListDetailDto.setTitle("빵이 맛있는 집 "+(i+1)+"호에서 만나요🥨");
            gatheringListDetailDto.setRecruitCnt(10);

            gatheringList.add(gatheringListDetailDto);
        }

        //카테고리 정보 세팅(test data)
        categoryList.add(new CategoryNameDto(1, "#코딩하기 좋은 카페💻"));
        categoryList.add(new CategoryNameDto(2, "#혼자 맥주한잔 하고싶을때🍺"));
        categoryList.add(new CategoryNameDto(3, "#노을맛집🌅"));
        categoryList.add(new CategoryNameDto(4, "#보기만해도 마음이 편해지는 바다🌊"));
        categoryList.add(new CategoryNameDto(5, "#담배피기 좋은 장소🚬"));
        categoryList.add(new CategoryNameDto(6, "#오늘은 차박🚘"));
        categoryList.add(new CategoryNameDto(7, "#소개팅으로 좋은 맛집🔥"));

        gatheringListDto.setGatheringList(gatheringList);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringList);

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

