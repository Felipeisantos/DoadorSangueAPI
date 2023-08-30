package com.felipe.DoadorSangueAPI.service.impl;

import com.felipe.DoadorSangueAPI.dto.AnaliseCandidatos;
import com.felipe.DoadorSangueAPI.entities.AnaliseCandidato;
import com.felipe.DoadorSangueAPI.entities.Pessoa;
import com.felipe.DoadorSangueAPI.repository.AnaliseCandidatoRepository;
import com.felipe.DoadorSangueAPI.service.AnaliseCandidatoService;
import com.felipe.DoadorSangueAPI.service.IO.JsonFileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AnaliseCandidatoImplements implements AnaliseCandidatoService {

    @Autowired
    AnaliseCandidatoRepository analiseCandidatoRepository;

    @Autowired
    JsonFileService jsonFileService;

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
    public Map<Pessoa.Sexo, Double> calcularPercentualObesosPorGenero(List<Pessoa> candidatos) {
        return candidatos.stream()
                .collect(Collectors.groupingBy(Pessoa::getSexo,
                        Collectors.collectingAndThen(Collectors.toList(), (List<Pessoa> pessoas) -> {
                            long totalObesos = pessoas.stream()
                                    .filter(pessoa -> calcularImc(pessoa) >= 30.0)
                                    .count();
                            long totalCountPerGender = pessoas.stream()
                                    .filter(pessoa -> pessoa.getSexo() == Pessoa.Sexo.MASCULINO || pessoa.getSexo() == Pessoa.Sexo.FEMININO)
                                    .count();
                            return (double) (totalObesos * 100) / totalCountPerGender;
                        })));
    }

    @Override
    public Map<String, Double> calcularMediaIdadePorTipoSanguineo(List<Pessoa> candidatos) {
        return candidatos.stream()
                .collect(Collectors.groupingBy(Pessoa::getTipoSanguineo,
                        Collectors.averagingDouble(pessoa -> obterAnos(pessoa.getDataNascimento()))));
    }

    @Override
    public Map<String, Long> calcularPotenciaisDoadoresPorTipoSanguineo(List<Pessoa> candidatos) {
        return candidatos.stream()
                .filter(this::canDonateBlood)
                .flatMap(candidato -> donationReceptorMap.getOrDefault(candidato.getTipoSanguineo(), Set.of()).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    public List<AnaliseCandidatos> obterTodasAnalises() {
        return analiseCandidatoRepository.findAll().stream().map(analiseCandidato -> {
            try {
                return jsonFileService.loadJsonFile(analiseCandidato.getCaminhoJson(), analiseCandidato.getNomeArquivo(), AnaliseCandidatos.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public AnaliseCandidatos obterAnalisePorId(Long id) throws EntityNotFoundException, IOException {
        return analiseCandidatoRepository.findById(id)
                .map(analiseCandidato -> {
                    try {
                        return jsonFileService.loadJsonFile(analiseCandidato.getCaminhoJson(), analiseCandidato.getNomeArquivo(), AnaliseCandidatos.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new EntityNotFoundException("Análise não encontrada com o ID: " + id));
    }

    private String calcularFaixaDeIdade(Pessoa pessoa) {
        int age = obterAnos(pessoa.getDataNascimento());
        int ageRange = age / 10 * 10; // Arredonda para a faixa de 10 anos mais próxima
        return (ageRange + 1) + " a " + (ageRange + 10);
    }

    private boolean canDonateBlood(Pessoa pessoa) {
        int age = obterAnos(pessoa.getDataNascimento());
        return age >= 16 && age <= 69 && pessoa.getPeso() > 50;
    }

    private double calcularImc(Pessoa pessoa) {
        double altura = pessoa.getAltura();
        return pessoa.getPeso() / (altura * altura);
    }

    private static int obterAnos(Date dataNascimento) {
        return Period.between(converteParaLocalDate(dataNascimento), LocalDate.now()).getYears();
    }

    private static LocalDate converteParaLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
