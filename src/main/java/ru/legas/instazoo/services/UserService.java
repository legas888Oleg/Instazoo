package ru.legas.instazoo.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.legas.instazoo.entity.User;
import ru.legas.instazoo.entity.enums.ERole;
import ru.legas.instazoo.payload.request.SignupRequest;
import ru.legas.instazoo.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(SignupRequest userIn){
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setSurname(userIn.getSurname());
        user.setMiddlename(userIn.getMiddlename());
        user.setMiddlename(userIn.getMiddlename());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRole().add(ERole.ROLE_USER);

        try{
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception ex){
            LOG.error("Error during registration: {}", ex.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }
}
