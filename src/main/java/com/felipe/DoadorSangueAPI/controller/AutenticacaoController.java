package com.felipe.DoadorSangueAPI.controller;

import com.felipe.DoadorSangueAPI.dao.request.SignUpRequest;
import com.felipe.DoadorSangueAPI.dao.request.SigninRequest;
import com.felipe.DoadorSangueAPI.dao.response.JwtAuthenticationResponse;
import com.felipe.DoadorSangueAPI.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(
            value = "/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Cadastro de novos usuários", description = "Cadastro de usuário")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return new ResponseEntity<>(autenticacaoService.signup(request), HttpStatus.OK);
    }

    @PostMapping(value = "/signin",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Login usuário", description = "Login de usuário")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return new ResponseEntity<>(autenticacaoService.signin(request), HttpStatus.OK);
    }
}
