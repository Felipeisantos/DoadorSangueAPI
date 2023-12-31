package com.felipe.DoadorSangueAPI.entities;

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

    static final long serialVersionUID = 2890482919066932519L;
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
