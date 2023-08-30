package com.felipe.DoadorSangueAPI.dto;

import com.felipe.DoadorSangueAPI.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseCandidatos {

    static final long serialVersionUID = -7382197912385370357L;

    private Map<String, Long> candidatoPorEstado;
    private Map<String, Double> imcMedioPorFaixaDeIdade;
    private Map<Pessoa.Sexo, Long> porcentagemObesidadePorGenero;
    private Map<String, Double> mediaIdadeTipoSanguineo;
    private Map<String, Long> potenciaisDoadoresPorTipoSanguineo;
}
