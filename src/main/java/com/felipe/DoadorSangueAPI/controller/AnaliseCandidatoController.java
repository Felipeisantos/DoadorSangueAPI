package com.felipe.DoadorSangueAPI.controller;


import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.entities.Usuario;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/v1/analise/")
public class AnaliseCandidatoController {

    @Autowired
    AnaliseCandidatoService analiseCandidatoService;

    @GetMapping(value = "listar")
    @Operation(summary = "Obtem todas as análises", description = "Retorna um Objeto contendo todas as análises")
    public ResponseEntity<?> listarAnalises() {
        try {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResponseEntity<>(analiseCandidatoService.obterTodosIdsAnalisesPeloIdUsuario(usuario.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento dos candidatos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Obtem análise por id", description = "Retorna um Objeto contendo a análise")
    public ResponseEntity<?> listarAnalisePorId(@PathVariable long id) {
        try {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AnaliseCandidatos analise = analiseCandidatoService.obterAnalisePorId(id,usuario);
            if (analise != null) {
                return new ResponseEntity<>(analise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Análise não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento da análise: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "processar-candidatos",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Processa candidatos a doação de sangue", description = "Retorna um Objeto contendo todos os dados processados")
    public ResponseEntity<?> processarDados(@RequestBody List<Pessoa> candidatos) {
        try {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AnaliseCandidatos analiseCandidatos = analiseCandidatoService.processarCandidatos(candidatos, usuario);
            return new ResponseEntity<>(analiseCandidatos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento dos candidatos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
