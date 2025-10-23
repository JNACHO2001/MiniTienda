package com.mycompany.main.Exepciones;

public class ErrorSistemaExepcion extends RuntimeException {

    public ErrorSistemaExepcion(String message) {
        super(message);
    }

    public ErrorSistemaExepcion(String message, Throwable cause) {
        super(message, cause);
    }

}
