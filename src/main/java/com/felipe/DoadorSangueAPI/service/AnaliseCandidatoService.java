package com.felipe.DoadorSangueAPI.service;

import com.felipe.DoadorSangueAPI.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AnaliseCandidatoService {
    Map<String, Long> calcularCandidatosPorEstado(List<Pessoa> candidatos);

    Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos);

    Map<Pessoa.Sexo, Long> calcularPercentualObesosPorGenero(List<Pessoa> candidatos);

    Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos);

    Map<String, Long> calcularQuantidadeDoadoresPorTipoSanguineo(List<Pessoa> candidatos);

}
