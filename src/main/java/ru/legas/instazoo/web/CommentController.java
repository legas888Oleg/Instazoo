package ru.legas.instazoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.legas.instazoo.dto.CommentDTO;
import ru.legas.instazoo.dto.PostDTO;
import ru.legas.instazoo.entity.Comment;
import ru.legas.instazoo.entity.Post;
import ru.legas.instazoo.facade.CommentFacade;
import ru.legas.instazoo.facade.PostFacade;
import ru.legas.instazoo.payload.response.MessageResponse;
import ru.legas.instazoo.services.CommentService;
import ru.legas.instazoo.services.PostService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentFacade commentFacade;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{postID}/create")
    public ResponseEntity<Object> createPost(
            @Valid @RequestBody CommentDTO commentDTO,
            @PathVariable("postID") String postID,
            BindingResult bindingResult,
            Principal principal){

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Comment comment = commentService.saveComment(Long.parseLong(postID), commentDTO, principal);

        CommentDTO createdCommentDTO = commentFacade.commentToCommentDIO(comment);
        return new ResponseEntity<>(createdCommentDTO, HttpStatus.OK);
    }

    @GetMapping("/{postID}/all")
    public ResponseEntity<List<CommentDTO>> getAllCommentsFromPost(@PathVariable("postID") String postID){
        List<CommentDTO> commentDTOList = commentService.getAllCommentsForPost(Long.parseLong(postID))
                .stream()
                .map(commentFacade::commentToCommentDIO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping("/{commentID}/delete")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable("commentID") String commentID, Principal principal){

        commentService.deleteComment(Long.parseLong(commentID));
        return new ResponseEntity<>(new MessageResponse("Comment was deleted"), HttpStatus.OK);
    }
}
