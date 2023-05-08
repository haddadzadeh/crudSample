package com.example.crudsample.security.user.auth;

import com.example.crudsample.security.user.dto.AuthenticationRequest;
import com.example.crudsample.security.user.dto.AuthenticationResponse;
import com.example.crudsample.security.user.dto.RegisterRequest;
import com.example.crudsample.security.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AutheniticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/autheniticate")
    public ResponseEntity<AuthenticationResponse> autheniticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authentication(request));
    }

}
