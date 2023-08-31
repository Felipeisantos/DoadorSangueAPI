package com.felipe.DoadorSangueAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoAnalise {

    private List<ChaveValor> candidatoPorEstado;
    private List<ChaveValorDouble> imcMedioPorFaixaDeIdade;
    private List<ChaveValorDouble> porcentagemObesidadePorGenero;
    private List<ChaveValorDouble> mediaIdadeTipoSanguineo;
    private List<ChaveValor> potenciaisDoadoresPorTipoSanguineo;
}
