package com.adlf.taskflow_api.exceptions;

public class TareaByIdNotFoundException extends RuntimeException {
    public TareaByIdNotFoundException(Long id) {
        super("La tarea con id " + id + " no fue encontrada");
    }
}
