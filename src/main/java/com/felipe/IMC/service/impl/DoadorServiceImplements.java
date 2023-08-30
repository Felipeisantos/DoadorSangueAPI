package com.felipe.IMC.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.IMC.model.Pessoa;
import com.felipe.IMC.service.AnalisePessoaService;
import com.felipe.IMC.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoadorServiceImplements implements DoadorService {

    @Autowired
    AnalisePessoaService analisePessoaService;

    @Override
    public List<Pessoa> processarCandidatos(String json) {
        try {
            List<Pessoa> pessoas = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
            analisePessoaService.calcularQuantidadeDoadoresPorTipoSanguineo(pessoas);
            return pessoas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
