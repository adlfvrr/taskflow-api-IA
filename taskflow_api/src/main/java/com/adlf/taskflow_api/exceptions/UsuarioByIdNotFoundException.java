package com.adlf.taskflow_api.exceptions;

public class UsuarioByIdNotFoundException extends RuntimeException{

    public UsuarioByIdNotFoundException(Long id){
        super("El usuario con id " + id + " no fue encontrado");
    }

}
