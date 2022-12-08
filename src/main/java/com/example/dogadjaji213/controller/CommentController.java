package com.example.dogadjaji213.controller;
import com.example.dogadjaji213.dto.CommentReqDto;
import com.example.dogadjaji213.model.Comment;
import com.example.dogadjaji213.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
    private final CommentService _commentService;
    @PutMapping("/{id}")
    public ResponseEntity<Comment> Post(@PathVariable UUID id, @RequestBody CommentReqDto commentReqDto){
        return ResponseEntity.ok().body(this._commentService.addComment(id,commentReqDto));
    }
}
