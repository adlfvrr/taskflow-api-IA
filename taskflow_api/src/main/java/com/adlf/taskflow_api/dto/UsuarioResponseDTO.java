package com.adlf.taskflow_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;

    public UsuarioResponseDTO(Long id, String nombre, String email, LocalDateTime fechaRegsitro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = fechaRegsitro;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}
