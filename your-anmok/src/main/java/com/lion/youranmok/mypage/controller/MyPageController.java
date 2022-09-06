package com.lion.youranmok.mypage.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.service.BookmarkService;
import com.lion.youranmok.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    private final CategoryService categoryService;

    // TODO 로그인 정보 추가 필요
    @GetMapping()
    public String myPage(Model model) {

        // TODO userId로 변경 필요
        List<CategoryDto> categories = categoryService.getBookmarkCategoriesByUser(0);

        model.addAttribute("categories", categories);

        return "/mypage/myPage";

    }


}
