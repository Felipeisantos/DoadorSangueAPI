package com.felipe.DoadorSangueAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaveValor {
    private String chave;
    private Long valor;
}
