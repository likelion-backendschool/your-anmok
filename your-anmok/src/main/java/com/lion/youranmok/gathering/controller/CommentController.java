package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.gathering.dto.CommentForm;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final GatheringCommentService commentService;


    private final GatheringService gatheringService;

    @PostMapping("/create/{id}")
    public String createComment(@PathVariable int id, CommentForm commentForm) {
//        System.out.println(commentForm.getContent());
//        System.out.println(commentForm.getMentionTo());
        GatheringBoard gatheringBoard = gatheringService.findById(id);
        commentService.create(gatheringBoard, commentForm);

        return "redirect:/gathering/%d".formatted(id);
    }
}
