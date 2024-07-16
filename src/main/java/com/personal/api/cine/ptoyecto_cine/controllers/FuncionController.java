package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.FuncionRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.FuncionesResponse;
import com.personal.api.cine.ptoyecto_cine.services.FuncionesImplement;

@RestController @RequestMapping("/funciones")
public class FuncionController {


    @Autowired
    private FuncionesImplement funcionService;


    @PostMapping("/create")
    public ResponseEntity<FuncionesResponse> create(@RequestBody FuncionRequest fun){
        return ResponseEntity.ok(funcionService.create(fun));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FuncionesResponse> update(@PathVariable Long id,@RequestBody FuncionRequest fun){
        return ResponseEntity.ok(funcionService.update(fun, id));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<FuncionesResponse> read(@PathVariable Long id){
        return ResponseEntity.ok(funcionService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        funcionService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
