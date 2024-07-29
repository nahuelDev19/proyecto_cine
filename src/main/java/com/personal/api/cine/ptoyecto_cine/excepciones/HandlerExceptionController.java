package com.personal.api.cine.ptoyecto_cine.excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDto> urlNotFound(Exception e){
        ErrorDto error= new ErrorDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError("direccion url no encontrada");
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(IdNotFoudException.class)
    public ResponseEntity<ErrorDto> idNotFound(IdNotFoudException e){
        ErrorDto error= new ErrorDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError("id no encontrado");
        return ResponseEntity.ok(error);   
    }


    @ExceptionHandler(TituloException.class)
    public ResponseEntity<ErrorDto> nombreExistente(TituloException e){
        ErrorDto error= new ErrorDto();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError("este nombre ya se encuentra,trate con otro");
        return ResponseEntity.ok(error);   
    }

    @ExceptionHandler(ClassNotExcist.class)
    public ResponseEntity<ErrorDto> nombreExistente(ClassNotExcist e){
        ErrorDto error= new ErrorDto();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        error.setDate(new Date());
        error.setError("busqueda sin exito");
        return ResponseEntity.ok(error);   
    }

}
