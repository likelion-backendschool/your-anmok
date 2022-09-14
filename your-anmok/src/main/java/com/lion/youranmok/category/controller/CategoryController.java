package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     */
    @GetMapping({"/home"})
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(required = false) String keyword) {

        System.out.println("CategoryController.home");

        List<GatheringPreviewDto> gatheringPreviewList = gatheringService.getPreview();

        Page<CategorySortingDto> categories = categoryService.getCategories(page, keyword);

        List<CategorySortingDto> recommendCategories = categoryService.getRecommendCategories();

        model.addAttribute("keyword", keyword);
        model.addAttribute("recommendCategories", recommendCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("gatheringList", gatheringPreviewList);

        return "/category/home";

    }


    /**
     * 카테고리 추가하는 메서드
     */
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto) {

        int id = categoryService.addCategory(categoryDto);

        if (id == 0) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);

    }
}
