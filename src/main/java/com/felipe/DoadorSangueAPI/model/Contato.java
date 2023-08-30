package com.felipe.DoadorSangueAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

    static final long serialVersionUID = 5700033947512862435L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    @Column(nullable = false)
    private String celular;
}
