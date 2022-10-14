package com.example.efthelper.service;

import com.example.efthelper.model.*;
import com.example.efthelper.model.projection.AuthResponseDTO;
import com.example.efthelper.model.projection.JwtResponseDTO;
import com.example.efthelper.model.projection.SignUpRequestDTO;
import com.example.efthelper.model.repository.HideoutStationRepository;
import com.example.efthelper.model.repository.QuestRepository;
import com.example.efthelper.model.repository.RoleRepository;
import com.example.efthelper.model.repository.UserRepository;
import com.example.efthelper.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    final private AuthenticationManager authenticationManager;

    final private JwtUtil jwtUtil;

    final private RoleRepository roleRepository;

    final private HideoutStationRepository stationRepository;

    final private QuestRepository questRepository;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       RoleRepository roleRepository,
                       HideoutStationRepository stationRepository,
                       QuestRepository questRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.stationRepository = stationRepository;
        this.questRepository = questRepository;
    }

    public ResponseEntity<?> login(LogInRequestDTO logInRequest){
        System.out.println(logInRequest.getPassword() + " " + logInRequest.getUsername());
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getUsername(), logInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        var userDetails = (UserPrincipal) authentication.getPrincipal();
        var roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        var jwtResponse = new JwtResponseDTO();
        System.out.println(jwt);
        jwtResponse.setToken(jwt);
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setRoles(roles);
        return ResponseEntity.ok(jwtResponse);
    }

    public ResponseEntity<AuthResponseDTO> signup(SignUpRequestDTO signUpRequest){
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
        userRepository.save(createUser(signUpRequest, hashedPassword, roles));
        return ResponseEntity.ok(new AuthResponseDTO("user registered successfully"));
    }

    private User createUser(SignUpRequestDTO signUpRequest, String hashedPassword, Set<Role> roles){
        var user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setRoles(roles);
        var stationsSet = new HashSet<HideoutStation>();
        var questsSet = new HashSet<Quest>();
        stationRepository.findAll().forEach(station -> stationsSet.add(station));
        questRepository.findAll().forEach(quest -> questsSet.add(quest));
        user.setHideoutStations(stationsSet);
        user.setQuests(questsSet);
        return user;

    }
}
