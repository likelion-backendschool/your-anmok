package com.lion.youranmok.login.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
<<<<<<< HEAD:your-anmok/src/main/java/com/lion/youranmok/gathering/controller/GatheringListController.java
@RequestMapping("/gathering")
public class GatheringListController {
    private final GatheringService gatheringService;
    private final CategoryService categoryService;
    private final GatheringCommentService gatheringCommentService;

    private final PlaceService placeService;


    @GetMapping("/list")
    public String getList(Model model) {
        List<GatheringListDetailDto> gatheringBoardList = gatheringService.listByCriteria();
        List<CategoryDto> categoryList = categoryService.findAll();


        model.addAttribute("categoryList", categoryList);
        model.addAttribute("gatheringList", gatheringBoardList);
=======
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/hi")
    public String getList() {
>>>>>>> 9410d61 (feature: take settings):your-anmok/src/main/java/com/lion/youranmok/login/controller/LoginController.java

        return "login/hi";
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
//        System.out.println("searchKeyword:"+searchKeyword);
        List<CreateSearchDto> dataList =  gatheringService.findCreateSearchResultByKeyword(searchKeyword);

//        System.out.println(dataList.size());
        mav.setViewName("jsonView");
        mav.addObject("result", true);
        mav.addObject("dataList", dataList);

        return mav;
    }

    @GetMapping("/modify/{id}")
    public String getGatheringBoardModify(@PathVariable int id, CreateForm createForm) {
//        System.out.println(createForm.toString());
        GatheringBoard gatheringBoard = gatheringService.findById(id);
        Place place = placeService.getPlace(id);
        if(gatheringBoard == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 게시글이 존재하지 않습니다.");
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

    @PostMapping("/modify/{id}")
    public String doGatheringBoardModify(@PathVariable int id, CreateForm createForm){
//        System.out.println(createForm.toString());
        gatheringService.modify(id, createForm);

        return "redirect:/gathering/%s".formatted(id);
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id) {
        gatheringService.delete(id);
        
        return "redirect:/gathering/list";
    }

}


