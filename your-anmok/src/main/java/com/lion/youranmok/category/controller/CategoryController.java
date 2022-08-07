package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("search")
    public String showList(String keyword, Model model) {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryService.findByTitleContaining(keyword);

        model.addAttribute("categories", categories);

        return "/home";
    }

}
