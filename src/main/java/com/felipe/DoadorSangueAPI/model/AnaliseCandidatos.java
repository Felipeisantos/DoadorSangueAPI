package com.felipe.DoadorSangueAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseCandidatos {
    private Map<String, Long> candidatoPorEstado;
    private Map<String, Double> imcMedioPorFaixaDeIdade;
    Map<Pessoa.Sexo, Long> porcentagemObesidadePorGenero;
    private Map<String, Double> mediaIdadeTipoSanguineo;
    private Map<String, Long> potenciaisDoadoresPorTipoSanguineo;
}
