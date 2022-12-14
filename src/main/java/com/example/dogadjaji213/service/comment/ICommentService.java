package com.example.dogadjaji213.service.comment;

import com.example.dogadjaji213.dto.CommentReqDto;
import com.example.dogadjaji213.model.Comment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ICommentService {
    Comment addComment(UUID id, CommentReqDto commentReqDto) throws Exception;
}
