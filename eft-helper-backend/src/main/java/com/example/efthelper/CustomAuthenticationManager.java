package com.example.efthelper;

import com.example.efthelper.service.MyUserDetailsService;
import com.example.efthelper.service.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


public class CustomAuthenticationManager implements AuthenticationProvider {


    final private MyUserDetailsService userDetailsService;

    public CustomAuthenticationManager(final MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var password = authentication.getCredentials() + "";
        System.out.println(username + " " + password + "---------------------------------2");
        var user = (UserPrincipal) userDetailsService.loadUserByUsername(username);
        if(password != user.getPassword()){
            throw new BadCredentialsException("1000");
        }
        if(!user.isEnabled())
            throw new BadCredentialsException("1001");
        return new UsernamePasswordAuthenticationToken(username, authentication.getCredentials(), user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
