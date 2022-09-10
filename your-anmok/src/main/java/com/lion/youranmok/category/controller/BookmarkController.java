package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/register")
    public String registerBookmark(@RequestParam("categoryId") int categoryId,
                                   @RequestParam(value = "page", required = false) int page,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   RedirectAttributes redirectAttributes) {

        bookmarkService.register(0, categoryId);

        redirectAttributes.addAttribute("keyword", keyword);
        redirectAttributes.addAttribute("page", page);

        return "redirect:/category/home";

    }

    @Transactional
    @GetMapping("/remove")
    public String removeBookmark(@RequestParam("categoryId") int categoryId, int page, String keyword, RedirectAttributes redirectAttributes) {

        bookmarkService.remove(0, categoryId);

        redirectAttributes.addAttribute("keyword", keyword);
        redirectAttributes.addAttribute("page", page);

        return "redirect:/category/home";

    }

    @Transactional
    @PostMapping("/removeByMypage")
    public String removeByMypage(@RequestParam int categoryId) {

        System.out.println("BookmarkController.removeByMypage");

        System.out.println("categoryId = " + categoryId);

        bookmarkService.remove(0, categoryId);



        return "redirect:/mypage";

    }


}
