package com.felipe.DoadorSangueAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "informacoesfisicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacoesFisicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private double altura;

    @Column(nullable = false)
    private double peso;

    @Column(name = "tipo_sanguineo", nullable = false)
    private String tipoSanguineo;
}
