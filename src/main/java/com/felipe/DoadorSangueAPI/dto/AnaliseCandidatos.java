package com.felipe.DoadorSangueAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class AnaliseCandidatos {

    static final long serialVersionUID = -400269345401625187L;
    private Long id;
    private ResultadoAnalise resultadoAnalise;
    @JsonIgnore
    private List<Pessoa> candidatos;

    public AnaliseCandidatos() {
        resultadoAnalise = new ResultadoAnalise();
    }


}
