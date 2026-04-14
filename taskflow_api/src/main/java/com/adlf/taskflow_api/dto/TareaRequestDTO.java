package com.adlf.taskflow_api.dto;

import com.adlf.taskflow_api.entity.Estado;
import com.adlf.taskflow_api.entity.Usuario;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TareaRequestDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descripcion;
    @NotNull
    private Estado estado;
    @FutureOrPresent
    private LocalDate fechaVencimiento;
    private Usuario usuario;

    public TareaRequestDTO(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
