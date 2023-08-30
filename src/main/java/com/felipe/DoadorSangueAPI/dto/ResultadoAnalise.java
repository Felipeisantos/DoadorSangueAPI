package com.felipe.DoadorSangueAPI.dto;

import com.felipe.DoadorSangueAPI.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoAnalise {
    static final long serialVersionUID = -6645723399665418584L;
    private Map<String, Long> candidatoPorEstado;
    private Map<String, Double> imcMedioPorFaixaDeIdade;
    private Map<Pessoa.Sexo, Double> porcentagemObesidadePorGenero;
    private Map<String, Double> mediaIdadeTipoSanguineo;
    private Map<String, Long> potenciaisDoadoresPorTipoSanguineo;
}
