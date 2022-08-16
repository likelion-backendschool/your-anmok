package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * url 접속시 초기 화면
     * 모든 카테고리 표시됨
     */
    @GetMapping({"", "/home"})
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

//        List<CategoryDto> categories = new ArrayList<>();

        Page<CategoryDto> categories = categoryService.getListByPaging(page);
        System.out.println(categories.getNumber());

        model.addAttribute("categories", categories);

        return "/category/home";

    }

    /**
     * 카테고리 검색 시 표시 화면
     * 해당하는 카테고리만 표시됨
     */

    @PostMapping("/search")
    public String showList(String keyword, Model model) {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryService.findByTagNameContaining(keyword);

        model.addAttribute("categories", categories);

        return "/category/home";
    }

}
