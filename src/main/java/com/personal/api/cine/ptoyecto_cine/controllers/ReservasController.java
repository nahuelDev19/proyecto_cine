package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.ReservaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.ReservasResponse;
import com.personal.api.cine.ptoyecto_cine.services.IReservaService;
import static com.personal.api.cine.ptoyecto_cine.uitils.ValidationResult.validation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las operaciones relacionadas con las reservas.
 */
@RestController
@RequestMapping("/reservas")
@Tag(name = "reservas", description = "Operaciones relacionadas con las reservas")
public class ReservasController {

    @Autowired
    private IReservaService reservaService;

    /**
     * Crea una nueva reserva.
     *
     * @param entity los detalles de la reserva.
     * @param result resultado de la validación de los detalles de la reserva.
     * @return una respuesta HTTP con la reserva creada.
     */
    @PostMapping("/create")
    @Operation(summary = "Crea una nueva reserva", description = "Crea reservas con los detalles proporcionados")
    public ResponseEntity<?> createReserva(@RequestBody ReservaRequest entity, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(reservaService.create(entity));
    }

    /**
     * Actualiza una reserva existente.
     *
     * @param request los nuevos detalles de la reserva.
     * @param result resultado de la validación de los detalles de la reserva.
     * @param id el ID de la reserva a actualizar.
     * @return una respuesta HTTP con la reserva actualizada.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza reservas", description = "Actualiza los detalles de reservas existentes")
    public ResponseEntity<?> updateReserva(@RequestBody ReservaRequest request, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(reservaService.update(request, id));
    }

    /**
     * Busca una reserva por su ID.
     *
     * @param id el ID de la reserva a buscar.
     * @return una respuesta HTTP con la reserva encontrada.
     */
    @GetMapping("/search/{id}")
    @Operation(summary = "Busca una reserva", description = "Busca una reserva por su ID")
    public ResponseEntity<ReservasResponse> searchReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.read(id));
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id el ID de la reserva a eliminar.
     * @return una respuesta HTTP indicando que la reserva fue eliminada.
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina reservas", description = "Elimina reservas por su ID")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    



}
