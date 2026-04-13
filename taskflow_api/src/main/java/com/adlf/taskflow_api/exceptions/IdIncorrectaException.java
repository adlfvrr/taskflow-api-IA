package com.adlf.taskflow_api.exceptions;

public class IdIncorrectaException extends RuntimeException {
    public IdIncorrectaException() {
        super("La id ingresada debe ser mayor a 0");
    }
}
