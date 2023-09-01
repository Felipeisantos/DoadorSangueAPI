package com.felipe.DoadorSangueAPI.service;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoadorService {
    AnaliseCandidatos processarCandidatos(List<Pessoa> candidatos) throws Exception;
}
