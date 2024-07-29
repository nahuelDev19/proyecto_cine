package com.personal.api.cine.ptoyecto_cine.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.PeliculaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.PeliculaResponse;
import com.personal.api.cine.ptoyecto_cine.services.PeliculaImplement;
import com.personal.api.cine.ptoyecto_cine.uitils.GeneroPelicula;
import static com.personal.api.cine.ptoyecto_cine.uitils.ValidationResult.validation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador para manejar las operaciones relacionadas con las películas.
 */
@RestController
@RequestMapping("/peliculas")
@Tag(name = "peliculas", description = "Operaciones relacionadas con las películas")
public class PeliculaController {

    @Autowired
    private PeliculaImplement peliService;

    /**
     * Crea una nueva película.
     *
     * @param request los detalles de la película.
     * @param result resultado de la validación de los detalles de la película.
     * @return una respuesta HTTP con la película creada.
     */
    @PostMapping("/create")
    @Operation(summary = "Crear una nueva película", description = "Crea una película con los detalles proporcionados")
    public ResponseEntity<?> createPeli(@Valid @RequestBody PeliculaRequest request, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(peliService.create(request));
    }

    /**
     * Muestra todas las películas de forma paginada.
     *
     * @param page número de página a recuperar.
     * @param size cantidad de elementos por página.
     * @param genero género de las películas a filtrar (opcional).
     * @return una respuesta HTTP con la página de películas.
     */
    @GetMapping("/page")
    @Operation(summary = "Muestra todas las películas", description = "Muestra todas las películas de forma paginada y también se hace un paginado por su género")
    public ResponseEntity<Page<PeliculaResponse>> getAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(required = false) GeneroPelicula genero) {
        var response = peliService.paginado(page, size, genero);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    /**
     * Actualiza una película existente.
     *
     * @param entity los nuevos detalles de la película.
     * @param result resultado de la validación de los detalles de la película.
     * @param id el ID de la película a actualizar.
     * @return una respuesta HTTP con la película actualizada.
     */
    @PutMapping("update/{id}")
    @Operation(summary = "Actualiza una película", description = "Actualiza los detalles de una película")
    public ResponseEntity<?> updatePeli(@Valid @RequestBody PeliculaRequest entity, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(peliService.update(entity, id));
    }

    /**
     * Busca una película por su ID.
     *
     * @param id el ID de la película a buscar.
     * @return una respuesta HTTP con la película encontrada.
     */
    @GetMapping("/search/{id}")
    @Operation(summary = "Busca una película", description = "Busca una película por su ID")
    public ResponseEntity<PeliculaResponse> searchPeli(@PathVariable Long id) {
        return ResponseEntity.ok(peliService.read(id));
    }

    /**
     * Elimina una película por su ID.
     *
     * @param id el ID de la película a eliminar.
     * @return una respuesta HTTP indicando que la película fue eliminada.
     */
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Elimina una película", description = "Elimina una película según su ID")
    public ResponseEntity<Void> deletePeli(@PathVariable Long id) {
        peliService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
