package ru.legas.instazoo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.legas.instazoo.services.CustomUserDetailsService;

import java.io.IOException;

@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private JWTTokenProvider jwtTokenProvider;

    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
