package com.lion.youranmok.gathering.controller;

import com.lion.youranmok.gathering.dto.CommentForm;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.entity.GatheringComment;
import com.lion.youranmok.gathering.service.GatheringCommentService;
import com.lion.youranmok.gathering.service.GatheringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final GatheringCommentService commentService;


    private final GatheringService gatheringService;

    @PostMapping("/create/{id}")
    public String createComment(@PathVariable int id, CommentForm commentForm) {
        GatheringBoard gatheringBoard = gatheringService.findById(id);
        commentService.create(gatheringBoard, commentForm);

        return "redirect:/gathering/%d".formatted(id);
    }

    @GetMapping("/delete/{id}/{commentId}")
    public ResponseEntity deleteComment(@PathVariable int id, @PathVariable int commentId) {
        GatheringComment gatheringComment = commentService.findById(commentId);

        if(gatheringComment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 댓글이 없습니다.");
        }
        if(gatheringComment.getReplyTo() == null) {
            gatheringComment.setCommentText("삭제된 댓글입니다.");
            commentService.save(gatheringComment);
        } else {
            commentService.delete(gatheringComment);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/modify/{id}/{commentId}")
    public String modifyComment(@PathVariable int id, @PathVariable int commentId, String content) {
        GatheringComment gatheringComment = commentService.findById(commentId);
        gatheringComment.setCommentText(content);

        commentService.save(gatheringComment);

        return "redirect:/gathering/%d".formatted(id);
    }
}
