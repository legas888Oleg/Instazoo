package ru.legas.instazoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.legas.instazoo.dto.CommentDTO;
import ru.legas.instazoo.entity.Comment;
import ru.legas.instazoo.entity.ImageModel;
import ru.legas.instazoo.facade.CommentFacade;
import ru.legas.instazoo.payload.response.MessageResponse;
import ru.legas.instazoo.services.CommentService;
import ru.legas.instazoo.services.ImageUploadService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(
            @RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file, principal);

        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"), HttpStatus.OK);
    }

    @PostMapping("/{postID}/upload")
    public ResponseEntity<MessageResponse> uploadImageToPost(
            @PathVariable("postID") String postID,
            @RequestParam("file") MultipartFile file,
            Principal principal) throws IOException {

        imageUploadService.uploadImageToPost(file, principal, Long.parseLong(postID));

        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"), HttpStatus.OK);
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal){
        ImageModel userImage = imageUploadService.getImageToUser(principal);

        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{postID}/image")
    public ResponseEntity<ImageModel> getImageForPost(
            @PathVariable("postID") String postID){
        ImageModel postImage = imageUploadService.getImageToPost(Long.parseLong(postID));

        return new ResponseEntity<>(postImage, HttpStatus.OK);
    }
}
