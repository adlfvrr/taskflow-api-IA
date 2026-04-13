package com.adlf.taskflow_api.exceptions;

public class TareasIncompletasException extends RuntimeException {
    public TareasIncompletasException() {
        super("El usuario posee tareas pendientes");
    }
}
