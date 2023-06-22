package ru.legas.instazoo.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.legas.instazoo.security.JWTTokenProvider;
import ru.legas.instazoo.services.UserService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
@AllArgsConstructor
public class AuthController {
    private JWTTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private ResponseErrorValidation responseErrorValidation;
    private UserService userService;
}
