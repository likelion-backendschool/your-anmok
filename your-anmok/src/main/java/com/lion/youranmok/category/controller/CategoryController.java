package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.gathering.service.GatheringService;
import com.lion.youranmok.security.dto.MemberContext;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final UserService userService;

    @Value("${Kakao_Client}")
    private String Kakao_Client;
    @Value("${Kakao_Callback}")
    private String Kakao_Callback;

    /**
     * url 접속시 초기 화면
     */
    @GetMapping({"/home"})
    public String home(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(required = false) String keyword,
                       @AuthenticationPrincipal MemberContext member) {

        int userId = -1;

        if (member != null) {
            userId = userService.findByUsername(member.getUsername()).getId();
        }

        List<GatheringPreviewDto> gatheringPreviewList = gatheringService.getPreview();
        Page<CategorySortingDto> categories = categoryService.getCategories(page, keyword, userId);

        List<CategorySortingDto> recommendCategories = categoryService.getRecommendCategories(userId);

        model.addAttribute("keyword", keyword);
        model.addAttribute("recommendCategories", recommendCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("gatheringList", gatheringPreviewList);
        model.addAttribute("callbackUrl", Kakao_Callback);
        model.addAttribute("clientId", Kakao_Client);

        return "category/home";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    public String showAddCategory(Model model){
        List<CategoryDto> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);

        return "category/addCategory";
    }


    /**
     * 카테고리 추가하는 메서드
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public String addCategory(String categoryName) {

        Category category = Category.builder()
                .tagName(categoryName)
                .build();

        //카테고리 등록
        Integer categoryid = categoryService.addCategory(categoryService.entityToDto(category));
        //이미 등록된 카테고리의 id 가져오기
        if(categoryid==0){
            categoryid = categoryService.getCategoryByTagName(categoryName).getId();
        }

//        Integer id = categoryService.addCategory(categoryDto);
//
//        if (id == 0) {
//            return new ResponseEntity(HttpStatus.CONFLICT);
//        }
//
//        return new ResponseEntity(HttpStatus.OK);
        return "redirect:/searchMap?categoryId="+categoryid;
    }
}
