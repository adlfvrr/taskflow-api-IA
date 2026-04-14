package com.adlf.taskflow_api.service;

import com.adlf.taskflow_api.dto.TareaMapper;
import com.adlf.taskflow_api.dto.TareaRequestDTO;
import com.adlf.taskflow_api.dto.TareaResponseDTO;
import com.adlf.taskflow_api.entity.Estado;
import com.adlf.taskflow_api.entity.Tarea;
import com.adlf.taskflow_api.entity.Usuario;
import com.adlf.taskflow_api.exceptions.IdIncorrectaException;
import com.adlf.taskflow_api.exceptions.TareaByIdNotFoundException;
import com.adlf.taskflow_api.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class TareaService {

    private TareaRepository tareaRepository;
    private UsuarioService usuarioService;

    public TareaService(TareaRepository tareaRepository, UsuarioService usuarioService) {
        this.tareaRepository = tareaRepository;
        this.usuarioService = usuarioService;
    }

    //Creamos una tarea y la asignamos al usuario
    public TareaResponseDTO crearTarea(TareaRequestDTO dto, Long idUsuario) {

        if (idUsuario <= 0 || idUsuario == null) {
            throw new IdIncorrectaException();
        }
        Tarea t = TareaMapper.toEntity(dto);
        //Asignamos la tarea al usuario mediante su respectivo service
        this.usuarioService.buscarEntityPorId(idUsuario).addTarea(t);
        /*
        Una manera más limpia sería
        Usuario u = this.usuarioService.buscarPorId(idUsuario);
        u.addTarea(tarea);
         */
        return TareaMapper.toResponseDTO(tareaRepository.save(t));
    }

    public List<TareaResponseDTO> listarTodas() {

        return tareaRepository.findAll().stream()
                .map(TareaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TareaResponseDTO buscarPorId(Long id) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }

        return TareaMapper.toResponseDTO(tareaRepository.findById(id).orElseThrow(() -> new TareaByIdNotFoundException(id)));
    }

    public List<TareaResponseDTO> listarPorUsuario(Long idUsuario) {
        List<Tarea> tareasUsuario = tareaRepository.findByUsuarioId(idUsuario);
        return tareasUsuario.stream()
                .map(TareaMapper::toResponseDTO)
                .collect(Collectors.toList());

    }

    public TareaResponseDTO actualizarTarea(TareaRequestDTO dto, Long id) {
        Tarea t = tareaRepository.findById(id).orElseThrow(() -> new TareaByIdNotFoundException(id));
        if (dto.getTitulo() != null) {
            t.setTitulo(dto.getTitulo());
        }
        if (dto.getDescripcion() != null) {
            t.setDescripcion(dto.getDescripcion());
        }
        if (dto.getEstado() != null) {
            t.setEstado(dto.getEstado());
        }
        if (dto.getFechaVencimiento() != null) {
            t.setFechaVencimiento(dto.getFechaVencimiento());
        }

        return TareaMapper.toResponseDTO(tareaRepository.save(t));
    }

    public void eliminarTarea(Long id) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }
            tareaRepository.deleteById(id);
    }

    public List<TareaResponseDTO> listarPorEstado(Estado estado){
        List<Tarea> tareasEstado = tareaRepository.findByEstado(estado);
        return tareasEstado.stream()
                .map(TareaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}
