package com.adlf.taskflow_api.dto;

import com.adlf.taskflow_api.entity.Estado;
import com.adlf.taskflow_api.entity.Tarea;
import com.adlf.taskflow_api.entity.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaMapper {

    public static TareaResponseDTO toResponseDTO(Tarea entity) {
        Long id = entity.getId();
        String titulo = entity.getTitulo();
        String descripcion = entity.getDescripcion();
        Estado estado = entity.getEstado();
        LocalDateTime fechaCreacion = entity.getFechaCreacion();
        LocalDate fechaVencimiento = entity.getFechaVencimiento();
        Long idUsuario = entity.getUsuario().getId();
        String nombreUsuario = entity.getUsuario().getNombre();
        return new TareaResponseDTO(id, titulo, descripcion, estado, fechaCreacion, fechaVencimiento, idUsuario, nombreUsuario);
    }

    public static Tarea toEntity(TareaRequestDTO dto) {
        Tarea t = new Tarea();
        t.setTitulo(dto.getTitulo());
        t.setUsuario(dto.getUsuario());
        t.setDescripcion(dto.getDescripcion());
        t.setEstado(dto.getEstado());
        t.setFechaVencimiento(dto.getFechaVencimiento());
        return t;
    }

}
