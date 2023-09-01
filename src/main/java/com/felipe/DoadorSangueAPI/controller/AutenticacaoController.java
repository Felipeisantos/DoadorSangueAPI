package com.felipe.DoadorSangueAPI.controller;

import com.felipe.DoadorSangueAPI.dao.request.SignUpRequest;
import com.felipe.DoadorSangueAPI.dao.request.SigninRequest;
import com.felipe.DoadorSangueAPI.dao.response.JwtAuthenticationResponse;
import com.felipe.DoadorSangueAPI.service.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
    private final AutenticacaoService autenticacaoService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(autenticacaoService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(autenticacaoService.signin(request));
    }
}
