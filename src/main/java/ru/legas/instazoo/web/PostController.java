package ru.legas.instazoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.legas.instazoo.dto.PostDTO;
import ru.legas.instazoo.dto.UserDTO;
import ru.legas.instazoo.entity.Post;
import ru.legas.instazoo.entity.User;
import ru.legas.instazoo.facade.PostFacade;
import ru.legas.instazoo.services.PostService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostFacade postFacade;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(
            @Valid @RequestBody PostDTO postDTO, BindingResult bindingResult, Principal principal){

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Post post = postService.createPost(postDTO, principal);

        PostDTO createdPost = postFacade.postToPostDIO(post);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }
}
