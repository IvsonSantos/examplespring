package com.ivson.cursomc.service.exceptions;

public class DataIntegritException extends RuntimeException {

    public DataIntegritException(String msg) {
        super(msg);
    }

    public DataIntegritException(String message, Throwable cause) {
        super(message, cause);
    }

}
