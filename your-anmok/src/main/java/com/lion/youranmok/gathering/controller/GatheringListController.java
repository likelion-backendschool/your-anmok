package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.entity.GatheringListCriteria;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.PlaceService;
import com.lion.youranmok.security.dto.MemberContext;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/gathering")
public class GatheringListController {
    private final GatheringService gatheringService;
    private final CategoryService categoryService;
    private final GatheringCommentService gatheringCommentService;

    private final PlaceService placeService;

    private final UserService userService;

    @Value("${Kakao_Client}")
    private String Kakao_Client;
    @Value("${Kakao_Callback}")
    private String Kakao_Callback;


    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal MemberContext member, Model model, @ModelAttribute GatheringListCriteria criteria) {

//        System.out.println(criteria.toString());

        List<GatheringListDetailDto> gatheringBoardList = gatheringService.listByCriteria(criteria);
        List<CategoryDto> categoryList = categoryService.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringBoardList);
        model.addAttribute("callbackUrl", Kakao_Callback);
        model.addAttribute("clientId", Kakao_Client);

        return "gathering/list";
    }


    @GetMapping("/{id}")
    public String getBoardDetail(@AuthenticationPrincipal MemberContext member, @PathVariable int id, Model model, CommentForm commentForm) {
        GatheringDetailDto gatheringDetailDto = gatheringService.getDetailById(id);
        List<CommentDto> commentList = gatheringCommentService.listByBoardId(id);
        for(CommentDto comment : commentList) {
            List<CommentDto> replyComment = gatheringCommentService.replyListByCommentId(comment.getCommentId());

            comment.setReplyList(replyComment);
        }

        model.addAttribute("detailList", gatheringDetailDto);
        model.addAttribute("commentList", commentList);

        return "gathering/detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createBoard(@AuthenticationPrincipal MemberContext member, Model model) {
        return "gathering/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@AuthenticationPrincipal MemberContext member, Model model, CreateForm createForm) {
        User user = userService.findByUsername(member.getUsername());
//        System.out.println(createForm.toString());
        GatheringBoard gatheringBoard = gatheringService.create(user, createForm);
        return "redirect:/gathering/%d".formatted(gatheringBoard.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/search")
    @ResponseBody
    public ModelAndView searchPlace(@AuthenticationPrincipal MemberContext member, @RequestParam String searchKeyword, ModelAndView mav) {
//        System.out.println("searchKeyword:"+searchKeyword);
        List<CreateSearchDto> dataList =  gatheringService.findCreateSearchResultByKeyword(searchKeyword);

//        System.out.println(dataList.size());
        mav.setViewName("jsonView");
        mav.addObject("result", true);
        mav.addObject("dataList", dataList);

        return mav;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String getGatheringBoardModify(@AuthenticationPrincipal MemberContext memberContext, @PathVariable int id, CreateForm createForm) {
//        System.out.println(createForm.toString());
        GatheringBoard gatheringBoard = gatheringService.findById(id);

        Place place = placeService.getPlace(gatheringBoard.getPlace().getId());
        if(gatheringBoard == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 게시글이 존재하지 않습니다.");
        }

        User user = userService.findByUsername(memberContext.getUsername());
        if(gatheringBoard.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 작성하지 않은 게시글은 수정이 불가능합니다.");
        }

        createForm.setGatherCnt(gatheringBoard.getGatherCnt());
        createForm.setContent(gatheringBoard.getText());
        createForm.setDate(java.sql.Date.valueOf(gatheringBoard.getDate()));
        createForm.setTitle(gatheringBoard.getTitle());
        createForm.setPlaceId(place.getId().intValue());
        createForm.setPlaceName(place.getName());
        createForm.setTotalCnt(gatheringBoard.getTotalCnt());

        return "gathering/modify_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String doGatheringBoardModify(@AuthenticationPrincipal MemberContext memberContext, @PathVariable int id, CreateForm createForm){
        GatheringBoard gatheringBoard = gatheringService.findById(id);

        if(gatheringBoard == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 게시글이 존재하지 않습니다.");
        }

        User user = userService.findByUsername(memberContext.getUsername());
        if(gatheringBoard.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 작성하지 않은 게시글은 수정이 불가능합니다.");
        }
        gatheringService.modify(id, createForm);

        return "redirect:/gathering/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteBoard(@AuthenticationPrincipal MemberContext memberContext, @PathVariable int id) {
        GatheringBoard gatheringBoard = gatheringService.findById(id);

        if(gatheringBoard == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 게시글이 존재하지 않습니다.");
        }

        User user = userService.findByUsername(memberContext.getUsername());

        if(gatheringBoard.getUserId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인이 작성하지 않은 게시글은 수정이 불가능합니다.");
        }

        gatheringService.delete(id);

        return "redirect:/gathering/list";
    }

}


