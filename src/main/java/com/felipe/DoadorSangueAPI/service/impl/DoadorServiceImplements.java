package com.felipe.DoadorSangueAPI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import com.felipe.DoadorSangueAPI.service.DoadorService;
import com.felipe.DoadorSangueAPI.service.IO.JsonFileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class DoadorServiceImplements implements DoadorService {

    @Autowired
    AnaliseCandidatoService analiseCandidatoService;

    @Autowired
    JsonFileService jsonFileService;

    @Value("${json.storagePath}")
    private String storagePath;

    @Override
    public AnaliseCandidatos processarCandidatos(List<Pessoa> candidatos) throws Exception {

        AnaliseCandidatos analiseCandidatos = new AnaliseCandidatos();
        analiseCandidatos.setCandidatos(candidatos);
        analiseCandidatos.getResultadoAnalise().setPotenciaisDoadoresPorTipoSanguineo(analiseCandidatoService.calcularPotenciaisDoadoresPorTipoSanguineo(candidatos));
        analiseCandidatos.getResultadoAnalise().setImcMedioPorFaixaDeIdade(analiseCandidatoService.calcularIMCMedioPorFaixaEtaria(candidatos));
        analiseCandidatos.getResultadoAnalise().setMediaIdadeTipoSanguineo(analiseCandidatoService.calcularMediaIdadePorTipoSanguineo(candidatos));
        analiseCandidatos.getResultadoAnalise().setCandidatoPorEstado(analiseCandidatoService.calcularCandidatosPorEstado(candidatos));
        analiseCandidatos.getResultadoAnalise().setPorcentagemObesidadePorGenero(analiseCandidatoService.calcularPercentualObesosPorGenero(candidatos));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String fileName = "analise.json";
            jsonFileService.saveJsonFile(objectMapper.writeValueAsString(analiseCandidatos), fileName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return analiseCandidatos;

    }
}
