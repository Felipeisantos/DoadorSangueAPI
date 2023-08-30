package com.felipe.IMC.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.felipe.IMC.deserializer.SexoDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Pessoa")
@Data
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @JsonProperty("data_nasc")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = SexoDeserializer.class)
    @Column(nullable = false)
    private Sexo sexo;

    private String mae;

    private String pai;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "informacoes_fisicas_id")
    private InformacoesFisicas informacoesFisicas;

    public enum Sexo {
        MASCULINO, FEMININO
    }

    public Pessoa() {
        this.contato = new Contato();
        this.informacoesFisicas = new InformacoesFisicas();
    }

    public String getEmail() {
        return this.contato.getEmail();
    }

    public void setEmail(String email) {
        this.contato.setEmail(email);
    }

    public String getCep() {
        return this.contato.getCep();
    }

    public void setCep(String cep) {
        this.contato.setCep(cep);
    }

    public String getEndereco() {
        return this.contato.getEndereco();
    }

    public void setEndereco(String endereco) {
        this.contato.setEndereco(endereco);
    }

    public int getNumero() {
        return this.contato.getNumero();
    }

    public void setNumero(int numero) {
        this.contato.setNumero(numero);
    }

    public String getBairro() {
        return this.contato.getBairro();
    }

    public void setBairro(String bairro) {
        this.contato.setBairro(bairro);
    }

    public String getCidade() {
        return this.contato.getCidade();
    }

    public void setCidade(String cidade) {
        this.contato.setCidade(cidade);
    }

    public String getEstado() {
        return this.contato.getEstado();
    }

    public void setEstado(String estado) {
        this.contato.setEstado(estado);
    }


    public String getTelefoneFixo() {
        return this.contato.getTelefoneFixo();
    }

    @JsonProperty("telefone_fixo")
    public void setTelefoneFixo(String telefoneFixo) {
        this.contato.setTelefoneFixo(telefoneFixo);
    }

    public String getCelular() {
        return this.contato.getCelular();
    }

    public void setCelular(String celular) {
        this.contato.setCelular(celular);
    }

    public BigDecimal getAltura() {
        return this.informacoesFisicas.getAltura();
    }

    public void setAltura(BigDecimal altura) {
        this.informacoesFisicas.setAltura(altura);
    }

    public BigDecimal getPeso() {
        return this.informacoesFisicas.getPeso();
    }

    public void setPeso(BigDecimal peso) {
        this.informacoesFisicas.setPeso(peso);
    }

    public String getTipoSanguineo() {
        return this.informacoesFisicas.getTipoSanguineo();
    }

    @JsonProperty("tipo_sanguineo")
    public void setTipoSanguineo(String tipoSanguineo) {
        this.informacoesFisicas.setTipoSanguineo(tipoSanguineo);
    }

}
