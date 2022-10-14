package com.example.efthelper.controller;


import com.example.efthelper.model.ERole;
import com.example.efthelper.model.LogInRequestDTO;
import com.example.efthelper.model.Role;
import com.example.efthelper.model.User;
import com.example.efthelper.model.projection.AuthResponseDTO;
import com.example.efthelper.model.projection.JwtResponseDTO;
import com.example.efthelper.model.projection.SignUpRequestDTO;
import com.example.efthelper.service.AuthService;
import com.example.efthelper.service.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final private AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequestDTO logInRequest){
        return authService.login(logInRequest);
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody SignUpRequestDTO signUpRequest){
        return authService.signup(signUpRequest);
    }


}
