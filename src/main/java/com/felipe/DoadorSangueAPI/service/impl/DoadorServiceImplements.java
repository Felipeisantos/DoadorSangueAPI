package com.felipe.DoadorSangueAPI.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.entities.Usuario;
import com.felipe.DoadorSangueAPI.repository.AnaliseCandidatoRepository;
import com.felipe.DoadorSangueAPI.repository.UsuarioRepository;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import com.felipe.DoadorSangueAPI.service.DoadorService;
import com.felipe.DoadorSangueAPI.service.IO.JsonFileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoadorServiceImplements implements DoadorService {

    @Autowired
    AnaliseCandidatoService analiseCandidatoService;

    @Autowired
    JsonFileService jsonFileService;

    @Autowired
    AnaliseCandidatoRepository analiseCandidatoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${json.storagePath}")
    private String storagePath;

    @Override
    @Transactional
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
            AnaliseCandidato analiseCandidato = new AnaliseCandidato();
            analiseCandidato.setCaminhoJson(System.getProperty("user.dir") + storagePath);
            analiseCandidato.setSolicitacaoUsuario(usuarioRepository.findById(1).orElse(new Usuario()));
            analiseCandidato.setDataRequisicao(new Date());
            analiseCandidato = analiseCandidatoRepository.save(analiseCandidato);
            analiseCandidato.setNomeArquivo("analise" + analiseCandidato.getId() + ".json");
            analiseCandidatos.setId(analiseCandidato.getId());
            analiseCandidato = analiseCandidatoRepository.save(analiseCandidato);

            jsonFileService.saveJsonFile(objectMapper.writeValueAsString(analiseCandidatos), analiseCandidato.getCaminhoJson(), analiseCandidato.getNomeArquivo());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        return analiseCandidatos;

    }
}
