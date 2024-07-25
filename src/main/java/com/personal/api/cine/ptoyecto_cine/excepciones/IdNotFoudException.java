package com.personal.api.cine.ptoyecto_cine.excepciones;

public class IdNotFoudException extends RuntimeException{

    private static final String error= "Id no encontrado en %s";

    public IdNotFoudException(String message){
        super(String.format(error, message));
    }

}
