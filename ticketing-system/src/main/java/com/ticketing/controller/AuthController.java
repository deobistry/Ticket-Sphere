package com.ticketing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketing.dto.request.LoginRequest;
import com.ticketing.dto.request.SignupRequest;
import com.ticketing.dto.response.LoginResponse;
import com.ticketing.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;


    public AuthController(AuthService authService) {

        this.authService = authService;
    }



    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody SignupRequest request
    ) {


        authService.signup(request);


        return ResponseEntity.ok(
                "User registered successfully"
        );
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {


        LoginResponse response =
                authService.login(request);


        return ResponseEntity.ok(response);
    }
}