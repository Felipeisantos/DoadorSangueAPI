package com.felipe.DoadorSangueAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class AnaliseCandidatos implements Serializable {

    @Serial
    private static final long serialVersionUID = -400269345401625187L;
    private Long id;
    private ResultadoAnalise resultadoAnalise;
    @JsonIgnore
    private List<Pessoa> candidatos;

    public AnaliseCandidatos() {
        resultadoAnalise = new ResultadoAnalise();
    }


}
