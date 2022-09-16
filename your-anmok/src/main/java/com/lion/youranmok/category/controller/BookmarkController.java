package com.lion.youranmok.category.controller;

import com.lion.youranmok.category.service.BookmarkService;
import com.lion.youranmok.security.dto.MemberContext;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    private final UserService userService;

    @GetMapping("/register")
    public String registerBookmark(@RequestParam("categoryId") int categoryId,
                                   @RequestParam(value = "page", required = false) int page,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal MemberContext member) {

        User user = userService.findByUsername(member.getUsername());

        bookmarkService.register(user.getId(), categoryId);

        redirectAttributes.addAttribute("keyword", keyword);
        redirectAttributes.addAttribute("page", page);

        return "redirect:/category/home";

    }

    @Transactional
    @GetMapping("/remove")
    public String removeBookmark(@RequestParam("categoryId") int categoryId,
                                 @RequestParam(value = "page", required = false) int page,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal MemberContext member) {

        User user = userService.findByUsername(member.getUsername());

        bookmarkService.remove(user.getId(), categoryId);

        redirectAttributes.addAttribute("keyword", keyword);
        redirectAttributes.addAttribute("page", page);

        return "redirect:/category/home";

    }

    @Transactional
    @PostMapping("/removeByMypage")
    public String removeByMypage(@RequestParam int categoryId, @AuthenticationPrincipal MemberContext member) {

        User user = userService.findByUsername(member.getUsername());

        bookmarkService.remove(user.getId(), categoryId);

        return "redirect:/mypage";

    }


}
