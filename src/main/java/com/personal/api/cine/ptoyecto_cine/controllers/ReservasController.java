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

@RestController @RequestMapping("/reservas")
@Tag(name = "reservas", description = "Operaciones relacionadas con las reservas")
public class ReservasController {

    @Autowired
    private IReservaService reservaService;

    @PostMapping("/create")
    @Operation(summary = "crea una nueva reserva", description = "crea reservas con los detalles proporcionados")
    public ResponseEntity<?> createReserva(@RequestBody ReservaRequest entity,BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }        
        return ResponseEntity.ok(reservaService.create(entity));
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "actualiza reservas", description = "actualiza los detalles de reservas existentes")
    public ResponseEntity<?> updateReserva(@RequestBody ReservaRequest request,BindingResult result,@PathVariable Long id){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(reservaService.update(request, id));
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "busca una reserva", description = "busca una reserva por su id")
    public ResponseEntity<ReservasResponse> searchReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "elimina reservas", description = "elimina reservas por su id")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    



}
