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

/**
 * Controlador para manejar las operaciones relacionadas con las funciones.
 */
@RestController
@RequestMapping("/funciones")
@Tag(name = "Funciones", description = "Operaciones relacionadas con las funciones")
public class FuncionController {

    @Autowired
    private FuncionesImplement funcionService;
    
    @Autowired
    private PeliculaRequestValidator peliValid;
    
    @Autowired
    private EnumValidator enums;

    /**
     * Inicializa el validador de datos.
     *
     * @param binder el binder de datos web.
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(enums);
    }

    /**
     * Crea una nueva función.
     *
     * @param fun los detalles de la función.
     * @param result resultado de la validación de los detalles de la función.
     * @return una respuesta HTTP con la función creada.
     */
    @PostMapping("/create")
    @Operation(summary = "Crear una nueva función", description = "Crea una función con los detalles proporcionados")
    public ResponseEntity<?> createFuncion(@Valid @RequestBody FuncionRequest fun, BindingResult result) {
        peliValid.validate(fun, result);
        enums.validate(fun, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }  
        FuncionesResponse response = funcionService.create(fun);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Actualiza una función existente.
     *
     * @param fun los nuevos detalles de la función.
     * @param result resultado de la validación de los detalles de la función.
     * @param id el ID de la función a actualizar.
     * @return una respuesta HTTP con la función actualizada.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza una función", description = "Actualiza los detalles de una función")
    public ResponseEntity<?> updateFuncion(@Valid @RequestBody FuncionRequest fun, BindingResult result, @PathVariable Long id) {
        peliValid.validate(fun, result);
        enums.validate(fun, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        } 
        FuncionesResponse response = funcionService.update(fun, id);
        return ResponseEntity.ok(response);
    }

    /**
     * Busca una función por su ID.
     *
     * @param id el ID de la función a buscar.
     * @return una respuesta HTTP con la función encontrada.
     */
    @GetMapping("/search/{id}")
    @Operation(summary = "Busca una función", description = "Busca una función por su ID")
    public ResponseEntity<FuncionesResponse> readFuncion(@PathVariable Long id) {
        return ResponseEntity.ok(funcionService.read(id));
    }

    /**
     * Elimina una función por su ID.
     *
     * @param id el ID de la función a eliminar.
     * @return una respuesta HTTP indicando que la función fue eliminada.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina una función", description = "Elimina una función según su ID")
    public ResponseEntity<Void> deleteFuncion(@PathVariable Long id) {
        funcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
