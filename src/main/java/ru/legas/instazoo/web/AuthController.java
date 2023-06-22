package ru.legas.instazoo.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.legas.instazoo.payload.request.LoginRequest;
import ru.legas.instazoo.payload.request.SignupRequest;
import ru.legas.instazoo.payload.response.JWTTokenSuccessResponse;
import ru.legas.instazoo.payload.response.MessageResponse;
import ru.legas.instazoo.security.JWTTokenProvider;
import ru.legas.instazoo.security.SecurityConstants;
import ru.legas.instazoo.services.UserService;
import ru.legas.instazoo.validations.ResponseErrorValidation;

import javax.validation.Valid;

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

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(
            @Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
