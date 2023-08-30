package com.felipe.DoadorSangueAPI.controller;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.entities.Usuario;
import com.felipe.DoadorSangueAPI.service.DoadorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    //http://localhost:8080/swagger-ui/index.html
    @PostMapping(
            value = "processar-candidatos",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Processa candidatos a doação de sangue", description = "Retorna um Objeto contendo todos os dados processados")
    public ResponseEntity<?> processarDados(@RequestBody List<Pessoa> candidatos) {
        try {
            AnaliseCandidatos analiseCandidatos = doadorService.processarCandidatos(candidatos);
            return new ResponseEntity<>(analiseCandidatos.getResultadoAnalise(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento dos candidatos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
