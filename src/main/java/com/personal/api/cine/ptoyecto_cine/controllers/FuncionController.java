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
    public ResponseEntity<FuncionesResponse> createFuncion(@RequestBody FuncionRequest fun){
        FuncionesResponse response= funcionService.create(fun);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FuncionesResponse> updateFuncion(@PathVariable Long id,@RequestBody FuncionRequest fun){
        FuncionesResponse response= funcionService.update(fun, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<FuncionesResponse> readFuncion(@PathVariable Long id){
        return ResponseEntity.ok(funcionService.read(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFuncion(@PathVariable Long id){
        funcionService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
