package ru.legas.instazoo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.legas.instazoo.dto.CommentDTO;
import ru.legas.instazoo.entity.Comment;
import ru.legas.instazoo.entity.Post;
import ru.legas.instazoo.entity.User;
import ru.legas.instazoo.exceptions.PostNotFoundException;
import ru.legas.instazoo.repositories.CommentRepository;
import ru.legas.instazoo.repositories.PostRepository;
import ru.legas.instazoo.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment saveComment(Long postID, CommentDTO commentDTO, Principal principal){
        User user = getUserByPrincipal(principal);
        Post post = postRepository.findById(postID)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found"));
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUserId(user.getId());
        comment.setUsername(user.getUsername());
        comment.setMessage(comment.getMessage());

        LOG.info("Saving comment for Post: {}", post.getId());

        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsForPost(Long postID){
        Post post = postRepository.findById(postID)
                .orElseThrow(() -> new PostNotFoundException("Post cannot be found"));

        return commentRepository.findAllByPost(post);
    }

    public void deleteComment(Long commentID){
        Optional<Comment> comment = commentRepository.findById(commentID);
        comment.ifPresent(commentRepository :: delete);
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
