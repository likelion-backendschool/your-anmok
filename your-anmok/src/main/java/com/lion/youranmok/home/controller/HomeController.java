package com.lion.youranmok.home.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping()
    public String home(Model model) {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryService.findAll();

        model.addAttribute("categories", categories);

        return "home";

    }

}
