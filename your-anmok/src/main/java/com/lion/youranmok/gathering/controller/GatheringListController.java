package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/gathering")
public class GatheringListController {
    private final GatheringService gatheringService;
    private final CategoryService categoryService;
    private final GatheringCommentService gatheringCommentService;


    @GetMapping("/list")
    public String getList(Model model) {
        List<GatheringListDetailDto> gatheringBoardList = gatheringService.listByCriteria();
        List<CategoryDto> categoryList = categoryService.findAll();


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringBoardList);

        return "gathering/list";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable int id, Model model, CommentForm commentForm) {
        GatheringDetailDto gatheringDetailDto = gatheringService.getDetailById(id);
        List<CommentDto> commentList = gatheringCommentService.listByBoardId(id);

        model.addAttribute("detailList", gatheringDetailDto);
        model.addAttribute("commentList", commentList);

        return "gathering/detail";
    }



}


