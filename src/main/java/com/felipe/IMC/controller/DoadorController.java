package com.felipe.IMC.controller;

import com.felipe.IMC.model.Pessoa;
import com.felipe.IMC.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @PostMapping(
            value = "processar-candidatos",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> processarDados(@RequestBody Pessoa[] json) {
        try {
            String campos = Arrays.toString(json);
            List<Pessoa> candidatosProcessados = doadorService.processarCandidatos(campos);
            return new ResponseEntity<>(candidatosProcessados, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Ocorreu um erro durante o processamento dos candidatos: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
