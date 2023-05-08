package com.example.crudsample.security.user.service;


import com.example.crudsample.security.user.config.JwtService;
import com.example.crudsample.security.user.dto.AuthenticationRequest;
import com.example.crudsample.security.user.dto.AuthenticationResponse;
import com.example.crudsample.security.user.dto.RegisterRequest;
import com.example.crudsample.security.user.entity.User;
import com.example.crudsample.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {//can be Interface

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest register) {
        User appUser = User.builder()
                .userName(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .email(register.getEmail())
                .role(register.getRole())//must change !it can't get from input value
                .build();
        userRepository.save(appUser);
        String token = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        User byUsername = userRepository.findByEmail(authenticationRequest.getUsername());
        if (Objects.isNull(byUsername)) {
            throw new UsernameNotFoundException("UsernameNotFoundException:" + authenticationRequest.getUsername());
        }
        String token = jwtService.generateToken(byUsername);
        return AuthenticationResponse.builder().token(token).build();
    }
}
