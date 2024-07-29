package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.UsuarioRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.UsuarioResponse;
import com.personal.api.cine.ptoyecto_cine.services.UsuarioImplement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.personal.api.cine.ptoyecto_cine.uitils.ValidationResult.validation;


@RestController @RequestMapping("/usuarios")
@Tag(name = "usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioImplement usuarioService;

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los detalles proporcionados")
    public ResponseEntity<?> createUser(@Valid @RequestBody UsuarioRequest param,BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }  
        return ResponseEntity.ok(usuarioService.create(param));
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "actualiza un usuario", description = "actualiza los detalles de un usuario")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UsuarioRequest usuario,BindingResult result ,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(usuarioService.update(usuario, id));
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "busca un usuario", description = "busca un usuario por su id")
    public ResponseEntity<UsuarioResponse> searchUser(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.read(id));
    }

    

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "elimina un usuario", description = "elimina un usuario segun su id")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
