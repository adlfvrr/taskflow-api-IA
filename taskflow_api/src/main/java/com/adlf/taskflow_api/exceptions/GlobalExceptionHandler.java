package com.adlf.taskflow_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsuarioByIdNotFoundException.class, TareaByIdNotFoundException.class, UsuarioByEmailNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleUsuarioNotFoundException(RuntimeException ex){
        Map<String, Object> exception = new HashMap<>();
        exception.put("timestamp", LocalDateTime.now());
        exception.put("status", HttpStatus.NOT_FOUND.value());
        exception.put("error", "Recurso no encontrado");
        exception.put("message", ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailIncorrectoException.class, IdIncorrectaException.class})
    public ResponseEntity<Map<String, Object>> handleIngresoIncorrectoException(RuntimeException ex){
        Map<String, Object> exception = new HashMap<>();
        exception.put("timestamp", LocalDateTime.now());
        exception.put("status", HttpStatus.BAD_REQUEST.value());
        exception.put("error", "Ingreso incorrecto de datos");
        exception.put("message", ex.getMessage());

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TareasIncompletasException.class, UsuarioExistenteException.class})
    public ResponseEntity<Map<String, Object>> handleErrorDeOperacionException(RuntimeException ex){
        Map<String, Object> exception = new HashMap<>();
        exception.put("timestamp", LocalDateTime.now());
        exception.put("status", HttpStatus.BAD_REQUEST.value());
        exception.put("error", "La operación no se puede realizar");
        exception.put("message", ex.getMessage());

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}
