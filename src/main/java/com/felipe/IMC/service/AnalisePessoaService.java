package com.felipe.IMC.service;

import com.felipe.IMC.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AnalisePessoaService {
    Map<String, Long> calcularCandidatosPorEstado(List<Pessoa> candidatos);

    Map<String, Long> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos);

    Map<String, Long> calcularPercentualObesosPorGenero(List<Pessoa> candidatos);

    Map<String, Long> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos);

    Map<String, Long> calcularQuantidadeDoadoresPorTipoSanguineo(List<Pessoa> candidatos);

}
