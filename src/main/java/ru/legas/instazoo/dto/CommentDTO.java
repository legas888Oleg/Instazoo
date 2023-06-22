package ru.legas.instazoo.dto;

import lombok.Data;
import ru.legas.instazoo.entity.Post;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CommentDTO {
    private Long id;
    private String username;
    private Post post;
    private Long userId;
    private String message;
}
