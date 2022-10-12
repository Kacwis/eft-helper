package com.example.efthelper.controller;

import com.example.efthelper.model.LogInRequestDTO;
import com.example.efthelper.model.projection.SignUpRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthTempController {

    @GetMapping("/signup")
    public String showSignUpPage(Model model){
        model.addAttribute("signUpRequest", new SignUpRequestDTO());
        return "signup";
    }

    @GetMapping("/login")
    public String showLogInPage(Model model){
        model.addAttribute("logInRequest", new LogInRequestDTO());
        return "login";
    }

}
