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

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/usuarios")
@Tag(name = "usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioImplement usuarioService;

    /**
     * Crea un nuevo usuario.
     *
     * @param param los detalles del usuario.
     * @param result resultado de la validación de los detalles del usuario.
     * @return una respuesta HTTP con el usuario creado.
     */
    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario con los detalles proporcionados")
    public ResponseEntity<?> createUser(@Valid @RequestBody UsuarioRequest param, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }  
        return ResponseEntity.ok(usuarioService.create(param));
    }
    
    /**
     * Actualiza un usuario existente.
     *
     * @param usuario los nuevos detalles del usuario.
     * @param result resultado de la validación de los detalles del usuario.
     * @param id el ID del usuario a actualizar.
     * @return una respuesta HTTP con el usuario actualizado.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "actualiza un usuario", description = "actualiza los detalles de un usuario")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UsuarioRequest usuario, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(usuarioService.update(usuario, id));
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario a buscar.
     * @return una respuesta HTTP con el usuario encontrado.
     */
    @GetMapping("/search/{id}")
    @Operation(summary = "busca un usuario", description = "busca un usuario por su id")
    public ResponseEntity<UsuarioResponse> searchUser(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.read(id));
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar.
     * @return una respuesta HTTP indicando que el usuario fue eliminado.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "elimina un usuario", description = "elimina un usuario según su ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
