package com.felipe.DoadorSangueAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseCandidatosInfo {
    private long id;
    private Date dataRequisicao;
}
