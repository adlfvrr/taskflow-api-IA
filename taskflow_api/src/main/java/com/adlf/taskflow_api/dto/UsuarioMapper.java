package com.adlf.taskflow_api.dto;

import com.adlf.taskflow_api.entity.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static UsuarioResponseDTO toResponseDTO(Usuario entity){
        Long id = entity.getId();
        String nombre = entity.getNombre();
        String email = entity.getEmail();
        LocalDateTime fechaRegistro = entity.getFechaRegistro();
        return new UsuarioResponseDTO(id, nombre, email, fechaRegistro);
    }

    public static Usuario toEntity(UsuarioRequestDTO dto){
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        return u;
    }

}
