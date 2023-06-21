package ru.legas.instazoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.legas.instazoo.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

}
