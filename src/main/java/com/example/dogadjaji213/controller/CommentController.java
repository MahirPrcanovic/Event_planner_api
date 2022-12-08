package com.example.dogadjaji213.controller;
import com.example.dogadjaji213.dto.CommentReqDto;
import com.example.dogadjaji213.dto.GlobalResponseDto;
import com.example.dogadjaji213.model.Comment;
import com.example.dogadjaji213.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
    private final CommentService _commentService;
    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponseDto> Post(@PathVariable UUID id, @RequestBody CommentReqDto commentReqDto){
        var response = new GlobalResponseDto();
        try{
            var comment = this._commentService.addComment(id,commentReqDto);
            if(comment == null) throw new Exception("Event does not exist.");
            response.setItem(Optional.of(comment));
            response.setMessage("Insertion successfull".describeConstable());
            return ResponseEntity.ok().body(response);
        }catch(Exception ex){
            response.setMessage(ex.getMessage().describeConstable());
            response.setSuccess(false);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
