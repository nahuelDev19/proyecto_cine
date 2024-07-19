package com.personal.api.cine.ptoyecto_cine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.api.cine.ptoyecto_cine.models.request.UsuarioRequest;
import com.personal.api.cine.ptoyecto_cine.models.responses.UsuarioResponse;
import com.personal.api.cine.ptoyecto_cine.services.UsuarioImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController @RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioImplement usuarioService;

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponse> createUser(@RequestBody UsuarioRequest param) {
        return ResponseEntity.ok(usuarioService.create(param));
    }
    

    @GetMapping("/search/{id}")
    public ResponseEntity<UsuarioResponse> searchUser(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.read(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioResponse> updateUser(@PathVariable Long id,@RequestBody UsuarioRequest usuario){
        return ResponseEntity.ok(usuarioService.update(usuario, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
