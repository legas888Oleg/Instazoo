package ru.legas.instazoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.legas.instazoo.dto.UserDTO;
import ru.legas.instazoo.entity.User;
import ru.legas.instazoo.facade.UserFacade;
import ru.legas.instazoo.services.UserService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        User user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDIO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
