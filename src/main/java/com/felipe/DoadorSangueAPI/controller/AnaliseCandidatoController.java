package com.felipe.DoadorSangueAPI.controller;


import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@RestController
@RequestMapping("/analise/")
public class AnaliseCandidatoController {

    @Autowired
    AnaliseCandidatoService analiseCandidatoService;

    @GetMapping(value = "listar")
    @Operation(summary = "Obtem todas as análises", description = "Retorna um Objeto contendo todas as análises")
    public ResponseEntity<?> listarAnalises() {
        try {
            return new ResponseEntity<>(analiseCandidatoService.obterTodosIdsAnalises(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento dos candidatos: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Obtem análise por id", description = "Retorna um Objeto contendo a análise")
    public ResponseEntity<?> listarAnalisePorId(@PathVariable long id) {
        try {
            AnaliseCandidatos analise = analiseCandidatoService.obterAnalisePorId(id); // Assuming you have a method to get a single analysis by ID
            if (analise != null) {
                return new ResponseEntity<>(analise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Análise não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Ocorreu um erro durante o processamento da análise: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
