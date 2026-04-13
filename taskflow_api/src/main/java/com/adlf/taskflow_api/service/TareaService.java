package com.adlf.taskflow_api.service;

import com.adlf.taskflow_api.entity.Tarea;
import com.adlf.taskflow_api.entity.Usuario;
import com.adlf.taskflow_api.exceptions.IdIncorrectaException;
import com.adlf.taskflow_api.exceptions.TareaByIdNotFoundException;
import com.adlf.taskflow_api.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
    public Tarea crearTarea(Tarea tarea, Long idUsuario) {

        if (idUsuario <= 0 || idUsuario == null) {
            throw new IdIncorrectaException();
        }
        //Asignamos la tarea al usuario mediante su respectivo service
        this.usuarioService.buscarPorId(idUsuario).addTarea(tarea);
        /*
        Una manera más limpia sería
        Usuario u = this.usuarioService.buscarPorId(idUsuario);
        u.addTarea(tarea);
         */
        tareaRepository.save(tarea);
        return tarea;
    }

    public List<Tarea> listarTodas() {

        return tareaRepository.findAll();
    }

    public Tarea buscarPorId(Long id) {
        if (id <= 0 || id == null) {
            throw new IdIncorrectaException();
        }

        return tareaRepository.findById(id).orElseThrow(() -> new TareaByIdNotFoundException(id));
    }

    public List<Tarea> listarPorUsuario(Long idUsuario) {
        return usuarioService.buscarPorId(idUsuario).getTareasUsuario();

    }

    public boolean actualizarTarea(Tarea tareaActualizada, Long id) {
        Tarea t = buscarPorId(id);
        if (tareaActualizada.getTitulo() != null) {
            t.setTitulo(tareaActualizada.getTitulo());
        }
        if (tareaActualizada.getDescripcion() != null) {
            t.setDescripcion(tareaActualizada.getDescripcion());
        }
        if (tareaActualizada.getEstado() != null) {
            t.setEstado(tareaActualizada.getEstado());
        }
        if (tareaActualizada.getFechaVencimiento() != null) {
            t.setFechaVencimiento(tareaActualizada.getFechaVencimiento());
        }

        tareaRepository.save(t);
        return true;
    }

    public boolean eliminarTarea(Long id) {
        if (id <= 0 || id == null) {
            tareaRepository.delete(buscarPorId(id));
        }
        return true;
    }

}
