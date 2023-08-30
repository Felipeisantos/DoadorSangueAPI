package com.felipe.IMC.service;

import com.felipe.IMC.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoadorService {
    List<Pessoa> processarCandidatos(String json);
}
