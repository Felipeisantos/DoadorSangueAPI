package com.felipe.DoadorSangueAPI.service.impl;

import com.felipe.DoadorSangueAPI.model.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.model.Pessoa;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import com.felipe.DoadorSangueAPI.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoadorServiceImplements implements DoadorService {

    @Autowired
    AnaliseCandidatoService analiseCandidatoService;

    @Override
    public AnaliseCandidatos processarCandidatos(List<Pessoa> candidatos) {
        try {
            AnaliseCandidatos analiseCandidatos = new AnaliseCandidatos();
            analiseCandidatos.setPotenciaisDoadoresPorTipoSanguineo(analiseCandidatoService.calcularQuantidadeDoadoresPorTipoSanguineo(candidatos));
            analiseCandidatos.setImcMedioPorFaixaDeIdade(analiseCandidatoService.calcularIMCMedioPorFaixaEtaria(candidatos));
            analiseCandidatos.setMediaIdadeTipoSanguineo(analiseCandidatoService.calcularMediaIdadePorTipoSanguineo(candidatos));
            analiseCandidatos.setCandidatoPorEstado(analiseCandidatoService.calcularCandidatosPorEstado(candidatos));
            analiseCandidatos.setPorcentagemObesidadePorGenero(analiseCandidatoService.calcularPercentualObesosPorGenero(candidatos));
            return analiseCandidatos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
