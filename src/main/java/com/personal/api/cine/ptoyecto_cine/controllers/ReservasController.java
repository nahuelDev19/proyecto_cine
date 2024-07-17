package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.ReservaRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.ReservasResponse;
import com.personal.api.cine.ptoyecto_cine.services.IReservaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController @RequestMapping("/reservas")
public class ReservasController {


    @Autowired
    private IReservaService reservaService;

    @PostMapping("/create")
    public ResponseEntity<ReservasResponse> create(@RequestBody ReservaRequest entity) {        
        return ResponseEntity.ok(reservaService.create(entity));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReservasResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.read(id));
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservasResponse> update(@PathVariable Long id,@RequestBody ReservaRequest request){
        return ResponseEntity.ok(reservaService.update(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    



}
