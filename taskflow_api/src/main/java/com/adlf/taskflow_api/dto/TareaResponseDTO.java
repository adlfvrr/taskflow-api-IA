package com.adlf.taskflow_api.dto;

import com.adlf.taskflow_api.entity.Estado;
import com.adlf.taskflow_api.entity.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaVencimiento;
    private Long idUsuario;
    private String nombreUsuario;

    public TareaResponseDTO(Long id, String titulo, String descripcion,
                            Estado estado, LocalDateTime fechaCreacion,
                            LocalDate fechaVencimiento, Long idUsuario, String nombreUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }


    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

}
