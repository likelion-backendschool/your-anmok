package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping("/list")
    public String getList(Model model) {
        List<GatheringListDetailDto> gatheringBoardList = gatheringService.listByCriteria();
        List<CategoryDto> categoryList = categoryService.findAll();


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringBoardList);

        return "gathering/list";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable int id, Model model, CommentForm commentForm) {
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

    @GetMapping("/create")
    public String createBoard(Model model) {
        return "gathering/create";
    }

    @PostMapping("/create")
    public String create(Model model, CreateForm createForm) {
        System.out.println(createForm.toString());
        GatheringBoard gatheringBoard = gatheringService.create(createForm);
        return "redirect:/gathering/%d".formatted(gatheringBoard.getId());
    }

    @GetMapping("/create/search")
    @ResponseBody
    public ModelAndView searchPlace(@RequestParam String searchKeyword, ModelAndView mav) {
        System.out.println("searchKeyword:"+searchKeyword);
        List<CreateSearchDto> dataList =  gatheringService.findCreateSearchResultByKeyword(searchKeyword);

        System.out.println(dataList.size());
        mav.setViewName("jsonView");
        mav.addObject("result", true);
        mav.addObject("dataList", dataList);

        return mav;
    }
}


