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
import org.springframework.web.bind.annotation.PutMapping;


@RestController @RequestMapping("/peliculas")
public class PeliculaController {


    @Autowired
    private PeliculaImplement peliService;

    /*@GetMapping
    public ResponseEntity<Page<HotelResponse>> geetAll(
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestHeader(required = false) SortType sortType){
            if (Objects.isNull(sortType)) sortType= SortType.NONE;
            var response= this.hotelService.reedAll(page, size, sortType);
            return response.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(response);
        }
    */

    @PostMapping("/agregando")
    public ResponseEntity<PeliculaResponse> create(@RequestBody PeliculaRequest request){
        return ResponseEntity.ok(peliService.create(request));
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<PeliculaResponse>> getAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(required = false) GeneroPelicula genero){
            var response= peliService.paginado(page, size, genero);
         return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
        }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<PeliculaResponse> update(@PathVariable Long id, @RequestBody PeliculaRequest entity) {        
        return ResponseEntity.ok(peliService.update(entity, id));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PeliculaResponse> buscar(@PathVariable Long id){
        return ResponseEntity.ok(peliService.read(id));
    }


    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
