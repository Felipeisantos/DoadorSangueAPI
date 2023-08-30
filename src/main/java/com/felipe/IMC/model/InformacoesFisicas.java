package com.felipe.IMC.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "informacoesfisicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacoesFisicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal altura;

    @Column(nullable = false)
    private BigDecimal peso;

    @Column(name = "tipo_sanguineo", nullable = false)
    private String tipoSanguineo;
}
