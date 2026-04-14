package com.adlf.taskflow_api.controller;

import com.adlf.taskflow_api.dto.UsuarioRequestDTO;
import com.adlf.taskflow_api.dto.UsuarioResponseDTO;
import com.adlf.taskflow_api.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public UsuarioResponseDTO agregar(@RequestBody UsuarioRequestDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO actualizarPorId(@PathVariable Long id, @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id,
                         @RequestParam(required = false, defaultValue = "false") boolean forzar) {
        usuarioService.eliminarUsuario(id, forzar);
    }


}
