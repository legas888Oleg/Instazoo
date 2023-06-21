package ru.legas.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legas.instazoo.entity.ImageModel;
import ru.legas.instazoo.entity.Post;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<Post> findByUserID(Long id);

    Optional<Post> findByPostID(Long id);

}
