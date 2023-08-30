package com.felipe.DoadorSangueAPI.service.impl;

import com.felipe.DoadorSangueAPI.model.Pessoa;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnaliseCandidatoImplements implements AnaliseCandidatoService {
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
    public Map<String, Double> calcularIMCMedioPorFaixaEtaria(List<Pessoa> candidatos) {
        return candidatos.stream()
                .collect(Collectors.groupingBy(this::calcularFaixaDeIdade,
                        Collectors.averagingDouble(this::calcularImc)));
    }

    @Override
    public Map<Pessoa.Sexo, Long> calcularPercentualObesosPorGenero(List<Pessoa> candidatos) {
        return candidatos.stream()
                .collect(Collectors.groupingBy(Pessoa::getSexo, Collectors.counting()));
    }

    @Override
    public Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos) {
        return candidatos.stream()
                .collect(Collectors.groupingBy(Pessoa::getTipoSanguineo,
                        Collectors.averagingDouble(pessoa -> obterAnos(converteParaLocalDate(pessoa.getDataNascimento())))));
    }

    @Override
    public Map<String, Long> calcularQuantidadeDoadoresPorTipoSanguineo(List<Pessoa> candidatos) {
        return candidatos.stream()
                .filter(this::canDonateBlood)
                .flatMap(candidato -> donationReceptorMap.getOrDefault(candidato.getTipoSanguineo(), Set.of()).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private String calcularFaixaDeIdade(Pessoa pessoa) {
        int age = obterAnos(converteParaLocalDate(pessoa.getDataNascimento()));
        int ageRange = age / 10 * 10; // Arredonda para a faixa de 10 anos mais próxima
        return (ageRange + 1) + " a " + (ageRange + 10);
    }

    private boolean canDonateBlood(Pessoa pessoa) {
        int age = obterAnos(converteParaLocalDate(pessoa.getDataNascimento()));
        return age >= 16 && age <= 69 && pessoa.getPeso() > 50;
    }

    private double calcularImc(Pessoa pessoa) {
        double altura = pessoa.getInformacoesFisicas().getAltura();
        double peso = pessoa.getInformacoesFisicas().getPeso();
        return peso / (altura * altura);
    }

    private static int obterAnos(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private static LocalDate converteParaLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
