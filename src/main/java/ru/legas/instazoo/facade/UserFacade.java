package ru.legas.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.legas.instazoo.dto.UserDTO;
import ru.legas.instazoo.entity.User;

@Component
public class UserFacade {
    public UserDTO userToUserDIO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
