package com.personal.api.cine.ptoyecto_cine.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;


@RestController @RequestMapping("/peliculas")
@Tag(name = "peliculas", description = "Operaciones relacionadas con las peliculas")
public class PeliculaController {


    @Autowired
    private PeliculaImplement peliService;

    @PostMapping("/create")
    @Operation(summary = "Crear una nuevo pelicula", description = "Crea una pelicula con los detalles proporcionados")
    public ResponseEntity<PeliculaResponse> createPeli(@RequestBody PeliculaRequest request){
        return ResponseEntity.ok(peliService.create(request));
    }

    @GetMapping("/page")
    @Operation(summary = "muestra de todas las peliculas", description = "muestra todas las peliculas de forma paginada y tabien se hace un paginado por su genero")
    public ResponseEntity<Page<PeliculaResponse>> getAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(required = false) GeneroPelicula genero){
            var response= peliService.paginado(page, size, genero);
         return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
        }

    @PutMapping("update/{id}")
    @Operation(summary = "actualiza una pelicula", description = "actualiza los detalles de una pelicua")
    public ResponseEntity<PeliculaResponse> updatePeli(@PathVariable Long id, @RequestBody PeliculaRequest entity) {        
        return ResponseEntity.ok(peliService.update(entity, id));
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "busca una pelicula", description = "busca una pelicula por su id")
    public ResponseEntity<PeliculaResponse> searchPeli(@PathVariable Long id){
        return ResponseEntity.ok(peliService.read(id));
    }


    @DeleteMapping("delete/{id}")
    @Operation(summary = "elimina una pelicula", description = "elimina una pelicula segun su id")
    public ResponseEntity<Void> deletePeli(@PathVariable Long id){
        peliService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
