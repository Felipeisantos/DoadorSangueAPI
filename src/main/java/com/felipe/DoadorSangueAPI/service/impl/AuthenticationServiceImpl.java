package com.felipe.DoadorSangueAPI.service.impl;


import com.felipe.DoadorSangueAPI.dao.request.SignUpRequest;
import com.felipe.DoadorSangueAPI.dao.request.SigninRequest;
import com.felipe.DoadorSangueAPI.dao.response.JwtAuthenticationResponse;
import com.felipe.DoadorSangueAPI.entities.Role;
import com.felipe.DoadorSangueAPI.entities.User;
import com.felipe.DoadorSangueAPI.repository.UserRepository;
import com.felipe.DoadorSangueAPI.service.AuthenticationService;
import com.felipe.DoadorSangueAPI.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            String jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
