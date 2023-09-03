package com.felipe.DoadorSangueAPI.service;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatosInfo;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.entities.Usuario;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface AnaliseCandidatoService {
    Map<String, Long> calcularCandidatosPorEstado(List<Pessoa> candidatos);

    Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos);

    Map<String, Double> calcularPercentualObesosPorGenero(List<Pessoa> candidatos);

    Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos);

    Map<String, Long> calcularPotenciaisDoadoresPorTipoSanguineo(List<Pessoa> candidatos);

    List<AnaliseCandidatosInfo> obterTodosIdsAnalisesPeloIdUsuario(Long id);

    List<AnaliseCandidatos> obterTodasAnalises();

    AnaliseCandidatos obterAnalisePorId(Long id, Usuario usuario) throws IOException;

    AnaliseCandidatos processarCandidatos(List<Pessoa> candidatos, Usuario usuario) throws Exception;

}
