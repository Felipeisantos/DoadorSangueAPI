package com.felipe.DoadorSangueAPI.service;

import com.felipe.DoadorSangueAPI.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AnaliseCandidatoService {
    Map<String, Long> calcularCandidatosPorEstado(List<Pessoa> candidatos) throws Exception;

    Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos) throws Exception;

    Map<Pessoa.Sexo, Long> calcularPercentualObesosPorGenero(List<Pessoa> candidatos) throws Exception;

    Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos) throws Exception;

    Map<String, Long> calcularQuantidadeDoadoresPorTipoSanguineo(List<Pessoa> candidatos) throws Exception;

}
