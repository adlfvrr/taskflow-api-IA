package com.adlf.taskflow_api.controller;

import com.adlf.taskflow_api.dto.TareaRequestDTO;
import com.adlf.taskflow_api.dto.TareaResponseDTO;
import com.adlf.taskflow_api.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private TareaService tareaService;

    public TareaController(TareaService tareaService){
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<TareaResponseDTO> obtenerTodas(){
        return tareaService.listarTodas();
    }

    @GetMapping("/{id}")
    public TareaResponseDTO obtenerPorId(@PathVariable Long id){
        return tareaService.buscarPorId(id);
    }

    @GetMapping("/usuario/{id}")
    public List<TareaResponseDTO> listarPorUsuario(@PathVariable Long id){
        return tareaService.listarPorUsuario(id);
    }

    @PostMapping
    public TareaResponseDTO crear(@Valid @RequestBody TareaRequestDTO dto, @RequestParam Long idUsuario){
        return tareaService.crearTarea(dto, idUsuario);
    }

    @PutMapping("/{id}")
    public TareaResponseDTO actualizar(@RequestBody TareaRequestDTO dto, @PathVariable Long id){
        return tareaService.actualizarTarea(dto, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        tareaService.eliminarTarea(id);
    }

}
