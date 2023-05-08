package com.example.crudsample.security.user.service;


import com.example.crudsample.security.user.entity.User;
import com.example.crudsample.security.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

       User appUser = userRepository.findByEmail(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException:" + username);
        }
        return appUser;
    }
}
