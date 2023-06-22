package ru.legas.instazoo.payload.request;

import lombok.Data;
import ru.legas.instazoo.annotations.PasswordMatches;
import ru.legas.instazoo.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {
    @Email(message = "It should hava email format")
    @NotBlank(message = "Your email is required")
    @ValidEmail
    private String email;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Please enter your surname")
    private String surname;

    @NotEmpty(message = "Please enter your middlename")
    private String middlename;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;

    private String confirmPassword;
}
