package com.personal.api.cine.ptoyecto_cine.excepciones;

public class TituloException extends RuntimeException{

    private static final String error= "el titulo %s ya existe";

    public TituloException(String message){
        super(String.format(error, message));
    }


}
