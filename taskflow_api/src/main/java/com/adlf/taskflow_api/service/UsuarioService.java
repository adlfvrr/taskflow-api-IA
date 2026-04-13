package com.adlf.taskflow_api.service;

import com.adlf.taskflow_api.entity.Usuario;
import com.adlf.taskflow_api.exceptions.*;
import com.adlf.taskflow_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UsuarioService {


    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        if(existsByEmail(usuario.getEmail())){
            throw new UsuarioExistenteException(usuario.getEmail());
        }
        usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id));
    }

    public boolean actualizarUsuario(Long id, Usuario usuarioActualizado) {
        if(id <= 0 || id == null){
            throw new IdIncorrectaException();
        }
        Usuario u = buscarPorId(id);
        if(usuarioActualizado.getNombre() != null){
            u.setNombre(usuarioActualizado.getNombre());
        }
        if(usuarioActualizado.getPassword() != null){
            u.setPassword(usuarioActualizado.getPassword());
        }
        if(usuarioActualizado.getEmail() != null){
            if(existsByEmail(usuarioActualizado.getEmail())){
                throw new UsuarioExistenteException(usuarioActualizado.getEmail());
            }
            u.setEmail(usuarioActualizado.getEmail());
        }
        if(usuarioActualizado.getTareasUsuario() != null){
            u.setTareasUsuario(usuarioActualizado.getTareasUsuario());
        }
        usuarioRepository.save(u);
        return true;
    }

    public boolean eliminarUsuario(Long id, boolean forzar) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }
        if (!forzar && !buscarPorId(id).getTareasUsuario().isEmpty()) {
            throw new TareasIncompletasException();
        }

        usuarioRepository.deleteById(id);
        return true;
    }

    public Usuario findByEmail(String email) {
        if (email.isEmpty() || email.isBlank()) {
            throw new EmailIncorrectoException();
        }
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioByEmailNotFoundException(email));
    }

    public boolean existsByEmail(String email) {
        if (email.isEmpty() || email.isBlank()) {
            throw new EmailIncorrectoException();
        }
        return usuarioRepository.existsByEmail(email);
    }

}
