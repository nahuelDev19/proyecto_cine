package com.personal.api.cine.ptoyecto_cine.uitils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationResult {

    public static final ResponseEntity<?> validation(BindingResult result){
        Map<String, Object> error= new HashMap<>();

        result.getFieldErrors().forEach(err-> {
            error.put(err.getField(), "el Campo: "+err.getField()+" "+ err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(error);
    }


}
