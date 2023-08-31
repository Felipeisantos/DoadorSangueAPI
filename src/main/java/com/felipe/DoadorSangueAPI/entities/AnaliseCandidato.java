package com.felipe.DoadorSangueAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "analise_candidato")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseCandidato {

    static final long serialVersionUID = -6947450209955778880L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_requisicao", nullable = false)
    private Date dataRequisicao;


    @Column(name = "caminho_json", nullable = false)
    private String caminhoJson;


    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User solicitacaoUsuario;


}