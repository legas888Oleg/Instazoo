package ru.legas.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.legas.instazoo.dto.PostDTO;
import ru.legas.instazoo.entity.Post;

@Component
public class PostFacade {
    public PostDTO postToPostDIO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setId(post.getId());
        postDTO.setCaption(post.getCaption());
        postDTO.setLikes(post.getLikes());
        postDTO.setUsersLiked(post.getLikedUsers());
        postDTO.setLocation(post.getLocation());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
