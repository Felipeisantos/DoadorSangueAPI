package com.felipe.DoadorSangueAPI.service;


import com.felipe.DoadorSangueAPI.dao.request.SignUpRequest;
import com.felipe.DoadorSangueAPI.dao.request.SigninRequest;
import com.felipe.DoadorSangueAPI.dao.response.JwtAuthenticationResponse;

public interface AutenticacaoService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

