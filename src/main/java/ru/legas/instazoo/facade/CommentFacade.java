package ru.legas.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.legas.instazoo.dto.CommentDTO;
import ru.legas.instazoo.entity.Comment;

@Component
public class CommentFacade {
    public CommentDTO commentToCommentDIO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setMessage(comment.getMessage());
        return commentDTO;
    }
}
