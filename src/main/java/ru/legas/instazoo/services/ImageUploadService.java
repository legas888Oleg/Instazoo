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
import ru.legas.instazoo.repositories.ImageRepository;
import ru.legas.instazoo.repositories.PostRepository;
import ru.legas.instazoo.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ImageUploadService {
    public static final Logger LOG = LoggerFactory.getLogger(ImageUploadService.class);

    private ImageRepository imageRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ImageUploadService(ImageRepository imageRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }




    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
