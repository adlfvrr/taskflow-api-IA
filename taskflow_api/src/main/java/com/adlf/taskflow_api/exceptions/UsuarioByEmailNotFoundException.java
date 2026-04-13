package com.adlf.taskflow_api.exceptions;

public class UsuarioByEmailNotFoundException extends RuntimeException {
    public UsuarioByEmailNotFoundException(String email) {
        super("El usuario con email " + email + " no fue encontrado");
    }
}
