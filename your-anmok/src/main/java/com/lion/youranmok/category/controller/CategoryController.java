package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;
    private final GatheringService gatheringService;

    /**
     * url 접속시 초기 화면
     * 모든 카테고리 표시됨
     */
    @GetMapping({"/home"})
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(required = false) String keyword) {

        System.out.println("CategoryController.home");

        List<GatheringPreviewDto> gatheringPreviewList = gatheringService.getPreview();

        Page<CategorySortingDto> categories;

        if (keyword != null) {
            categories = categoryService.findByTagNameContaining(page, keyword);
            model.addAttribute("keyword", keyword);
        }
        else {
            categories = categoryService.getListByPaging(page);
            System.out.println("categories.getContent() = " + categories.getContent());
        }

        List<CategorySortingDto> recommendCategories = categoryService.getRecommendCategories();

        model.addAttribute("recommendCategories", recommendCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("gatheringList", gatheringPreviewList);

        return "/category/home";

    }

}
