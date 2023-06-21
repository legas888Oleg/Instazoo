package ru.legas.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legas.instazoo.entity.Comment;
import ru.legas.instazoo.entity.Post;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    Comment findByPostIdAndUserID(Long postID, Long userID);

}
