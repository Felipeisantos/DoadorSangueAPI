package com.felipe.DoadorSangueAPI.service.impl;


import com.felipe.DoadorSangueAPI.dao.request.SignUpRequest;
import com.felipe.DoadorSangueAPI.dao.request.SigninRequest;
import com.felipe.DoadorSangueAPI.dao.response.JwtAuthenticationResponse;
import com.felipe.DoadorSangueAPI.entities.Role;
import com.felipe.DoadorSangueAPI.entities.Usuario;
import com.felipe.DoadorSangueAPI.repository.UsuarioRepository;
import com.felipe.DoadorSangueAPI.service.AutenticacaoService;
import com.felipe.DoadorSangueAPI.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticacaoServiceImpl implements AutenticacaoService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Usuario usuario = Usuario.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        usuarioRepository.save(usuario);
        String jwt = jwtService.generateToken(usuario);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(usuario);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
