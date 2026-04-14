package com.adlf.taskflow_api.service;

import com.adlf.taskflow_api.dto.UsuarioMapper;
import com.adlf.taskflow_api.dto.UsuarioRequestDTO;
import com.adlf.taskflow_api.dto.UsuarioResponseDTO;
import com.adlf.taskflow_api.entity.Usuario;
import com.adlf.taskflow_api.exceptions.*;
import com.adlf.taskflow_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        if (existsByEmail(dto.getEmail())) {
            throw new UsuarioExistenteException(dto.getEmail());
        }
        Usuario entity = UsuarioMapper.toEntity(dto);
        return UsuarioMapper.toResponseDTO(usuarioRepository.save(entity));
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }

        return UsuarioMapper.toResponseDTO(usuarioRepository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id)));
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }
        Usuario u = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id));
        if (dto.getNombre() != null) {
            u.setNombre(dto.getNombre());
        }
        if (dto.getPassword() != null) {
            u.setPassword(dto.getPassword());
        }
        if (dto.getEmail() != null) {
            if (existsByEmail(dto.getEmail())) {
                throw new UsuarioExistenteException(dto.getEmail());
            }
            u.setEmail(dto.getEmail());
        }
        return UsuarioMapper.toResponseDTO(usuarioRepository.save(u));
    }

    public void eliminarUsuario(Long id, boolean forzar) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }
        Usuario u = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id));
        if (!forzar && !u.getTareasUsuario().isEmpty()) {
            throw new TareasIncompletasException();
        }

        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO findByEmail(String email) {
        if (email.isEmpty() || email.isBlank()) {
            throw new EmailIncorrectoException();
        }
        return UsuarioMapper.toResponseDTO(usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioByEmailNotFoundException(email)));
    }

    public boolean existsByEmail(String email) {
        if (email.isEmpty() || email.isBlank()) {
            throw new EmailIncorrectoException();
        }
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarEntityPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id));
    }

}
