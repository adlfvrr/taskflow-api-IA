package com.adlf.taskflow_api.exceptions;

public class EmailIncorrectoException extends RuntimeException {
    public EmailIncorrectoException() {
        super("El email ingresado es incorrecto");
    }
}
