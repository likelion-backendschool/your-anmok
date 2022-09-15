package com.lion.youranmok.mypage.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.CommentMyPageDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    private final GatheringService gatheringService;

    private final GatheringCommentService gatheringCommentService;

    // TODO 로그인 정보 추가 필요
    @GetMapping()
    public String myPage(Model model) {

        // TODO userId로 변경 필요
        List<CategoryDto> categories = categoryService.getBookmarkCategoriesByUser(1);
        User user = userRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        List<GatheringListDetailDto> myGatheringList = gatheringService.getDetailByUserId(1);
        List<CommentMyPageDto> myCommentList = gatheringCommentService.listByUserId(1);


        model.addAttribute("categories", categories);
        model.addAttribute("member", user);
        model.addAttribute("gatheringList", myGatheringList);
        model.addAttribute("commentList", myCommentList);

        return "/mypage/myPage";

    }


}
