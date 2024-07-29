package com.personal.api.cine.ptoyecto_cine.uitils.validaciones;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.personal.api.cine.ptoyecto_cine.uitils.SalaDeCine;

public class SalaDeCineDeserializer extends JsonDeserializer<SalaDeCine>{

    @Override
    public SalaDeCine deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getText();
        if ("null".equalsIgnoreCase(value)) {
            return null; // Maneja "null" explícito como valor nulo
        }
        try {
            return SalaDeCine.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Maneja valores no válidos como nulos
        }
    }
}
