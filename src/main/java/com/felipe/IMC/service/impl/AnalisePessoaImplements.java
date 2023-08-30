package com.felipe.IMC.service.impl;

import com.felipe.IMC.model.Pessoa;
import com.felipe.IMC.service.AnalisePessoaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnalisePessoaImplements implements AnalisePessoaService {
    private static final Map<String, Set<String>> donationReceptorMap = Map.of(
            "A+", Set.of("AB+", "A+"),
            "A-", Set.of("A+", "A-", "AB+", "AB-"),
            "B+", Set.of("B+", "AB+"),
            "B-", Set.of("B+", "B-", "AB+", "AB-"),
            "AB+", Set.of("AB+"),
            "AB-", Set.of("AB+", "AB-"),
            "O+", Set.of("A+", "B+", "O+", "AB+"),
            "O-", Set.of("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-")
    );

    @Override
    public Map<String, Long> calcularCandidatosPorEstado(List<Pessoa> candidatos) {
        return candidatos.stream().collect(Collectors.groupingBy(Pessoa::getEstado, Collectors.counting()));
    }

    @Override
    public Map<String, Long> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos) {
        // Implemente aqui o cálculo do IMC médio por faixa etária
        return null;
    }

    @Override
    public Map<String, Long> calcularPercentualObesosPorGenero(List<Pessoa> candidatos) {
        // Implemente aqui o cálculo do percentual de obesos por gênero
        return null;
    }

    @Override
    public Map<String, Long> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos) {
        // Implemente aqui o cálculo da média de idade por tipo sanguíneo
        return null;
    }

    @Override
    public Map<String, Long> calcularQuantidadeDoadoresPorTipoSanguineo(List<Pessoa> candidatos) {

        Map<String, Long> donorsByTypeRecipient = candidatos.stream()
                .filter(this::canDonateBlood)
                .flatMap(candidato -> donationReceptorMap.getOrDefault(candidato.getTipoSanguineo(), Set.of()).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Quantidade de possíveis doadores por tipo sanguíneo receptor:");
        donorsByTypeRecipient.forEach((donatorType, amount) -> {
            System.out.println(donatorType + ": " + amount);
        });
        return donorsByTypeRecipient;
    }

    public boolean canDonateBlood(Pessoa pessoa) {
        int age = getAge(convertToLocalDate(pessoa.getDataNascimento()));
        BigDecimal weight = pessoa.getPeso();
        return age >= 16 && age <= 69 && weight.compareTo(BigDecimal.valueOf(50)) > 0;
    }


    private static int getAge(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
