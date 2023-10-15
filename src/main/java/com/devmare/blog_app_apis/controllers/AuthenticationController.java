package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.JwtAuthenticationRequest;
import com.devmare.blog_app_apis.security.CustomUserDetailService;
import com.devmare.blog_app_apis.security.JwtAuthenticationResponse;
import com.devmare.blog_app_apis.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthenticationController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //! http://localhost:8081/api/auth/login

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(
            @RequestBody JwtAuthenticationRequest request
    ) {
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUsername());
        String generatedToken = jwtTokenHelper.generateToken(userDetails);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(generatedToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
