package com.adlf.taskflow_api.exceptions;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String email) {
        super("El usuario con el mail " + email + " ya existe");
    }
}
