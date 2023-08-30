package com.felipe.IMC.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.felipe.IMC.model.Pessoa;

import java.io.IOException;

public class SexoDeserializer extends JsonDeserializer<Pessoa.Sexo> {

    @Override
    public Pessoa.Sexo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String sexo = p.getText();
        return Pessoa.Sexo.valueOf(sexo.toUpperCase()); // Convertendo para maiúsculas
    }
}
