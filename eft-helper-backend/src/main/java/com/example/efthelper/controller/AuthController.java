package com.example.efthelper.controller;


import com.example.efthelper.model.ERole;
import com.example.efthelper.model.LogInRequestDTO;
import com.example.efthelper.model.Role;
import com.example.efthelper.model.User;
import com.example.efthelper.model.projection.AuthResponseDTO;
import com.example.efthelper.model.projection.JwtResponseDTO;
import com.example.efthelper.model.projection.SignUpRequestDTO;
import com.example.efthelper.model.repository.RoleRepository;
import com.example.efthelper.model.repository.UserRepository;
import com.example.efthelper.service.UserPrincipal;
import com.example.efthelper.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    final private AuthenticationManager authenticationManager;

    final private JwtUtil jwtUtil;

    final private RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequestDTO logInRequest){
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        var userDetails = (UserPrincipal) authentication.getPrincipal();
        var roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        var jwtResponse = new JwtResponseDTO();
        jwtResponse.setToken(jwt);
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setRoles(roles);
        return ResponseEntity.ok(jwtResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody SignUpRequestDTO signUpRequest){
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponseDTO("username is already taken"));
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponseDTO("email is already taken"));
        }
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByRole(ERole.ROLE_USER);
        if(userRole.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthResponseDTO("There is no role like this"));
        }
        roles.add(userRole.get());
        var user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new AuthResponseDTO("user registered successfully"));
    }


}
