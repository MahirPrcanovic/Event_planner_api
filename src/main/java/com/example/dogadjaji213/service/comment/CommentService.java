package com.example.dogadjaji213.service.comment;

import com.example.dogadjaji213.dto.CommentReqDto;
import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Comment;
import com.example.dogadjaji213.model.Event;
import com.example.dogadjaji213.repository.CommentRepository;
import com.example.dogadjaji213.repository.EventRepository;
import com.example.dogadjaji213.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final CommentRepository _commentRepository;
    private final EventRepository _eventRepository;
    private final UserRepository _userRepository;
    @Override
    public Comment addComment(UUID id, CommentReqDto commentReqDto) throws Exception{
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = this._userRepository.findByEmail(auth);
        if(appUser == null) throw new IllegalStateException("User not found.");
        if(appUser.getIsBanned()) throw new IllegalStateException("You can't comment due to your ban.");
        Optional<Event> event = this._eventRepository.findById(id);
        if(event.isPresent()){
            Comment comment = this._commentRepository.save(new Comment(commentReqDto.getComment(),appUser,event.get(), LocalDate.now()));
            return comment;
        }
        return null;
    }
}
