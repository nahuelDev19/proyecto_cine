package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.FuncionesResponse;
import com.personal.api.cine.ptoyecto_cine.services.FuncionesImplement;
import com.personal.api.cine.ptoyecto_cine.uitils.validaciones.EnumValidator;
import com.personal.api.cine.ptoyecto_cine.uitils.validaciones.PeliculaRequestValidator;

import static com.personal.api.cine.ptoyecto_cine.uitils.ValidationResult.validation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController @RequestMapping("/funciones")
@Tag(name = "Funciones", description = "Operaciones relacionadas con las funciones")
public class FuncionController {


    @Autowired
    private FuncionesImplement funcionService;
    @Autowired
    private PeliculaRequestValidator peliValid;
    @Autowired
    private EnumValidator enums;

     @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(enums);
    }


    @PostMapping("/create")
    @Operation(summary = "Crear una nueva funcion", description = "Crea una funcion con los detalles proporcionados")
    public ResponseEntity<?> createFuncion(@Valid @RequestBody FuncionRequest fun,BindingResult result){
        peliValid.validate(fun, result);
        enums.validate(fun, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }  
        FuncionesResponse response= funcionService.create(fun);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "actualiza una funcion", description = "actualiza los detalles de una funcion")
    public ResponseEntity<?> updateFuncion(@Valid @RequestBody FuncionRequest fun,BindingResult result,@PathVariable Long id){
        peliValid.validate(fun, result);
        enums.validate(fun, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        } 
        FuncionesResponse response= funcionService.update(fun, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "busca una funcion", description = "busca una funcion por su id")
    public ResponseEntity<FuncionesResponse> readFuncion(@PathVariable Long id){
        return ResponseEntity.ok(funcionService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "elimina una funcion", description = "elimina una funcion segun su id")
    public ResponseEntity<Void> deleteFuncion(@PathVariable Long id){
        funcionService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
