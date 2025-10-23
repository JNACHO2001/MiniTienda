package com.mycompany.main.Exepciones;

public class RegistroDuplicadoException extends RuntimeException {

    public RegistroDuplicadoException(String message) {
        super(message);
    }

    public RegistroDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }

}
