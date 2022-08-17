package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gathering")
public class GatheringListController {
    private final GatheringService gatheringService;
    private final CategoryService categoryService;

    GatheringListController(GatheringService gatheringService, CategoryService categoryService) {
        this.gatheringService = gatheringService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<GatheringListDetailDto> gatheringBoardList = gatheringService.listByCriteria();
        List<CategoryDto> categoryList = categoryService.findAll();


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringBoardList);

        return "gathering/list";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable int id, Model model) {
        GatheringDetailDto gatheringDetailDto = gatheringService.getDetailById(id);

        model.addAttribute("gatheringDetail", gatheringDetailDto);

        return "gathering/detail";
    }

}


