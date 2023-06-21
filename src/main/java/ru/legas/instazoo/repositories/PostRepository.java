package ru.legas.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legas.instazoo.entity.Post;
import ru.legas.instazoo.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllByOrderByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, User user);

}
