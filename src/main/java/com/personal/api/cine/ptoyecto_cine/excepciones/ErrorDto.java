package com.personal.api.cine.ptoyecto_cine.excepciones;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDto {

    private String message;
    private String error;
    private int status;
    private Date date;

}
