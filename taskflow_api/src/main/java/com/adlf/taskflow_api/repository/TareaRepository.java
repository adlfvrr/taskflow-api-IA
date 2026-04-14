package com.adlf.taskflow_api.repository;

import com.adlf.taskflow_api.entity.Estado;
import com.adlf.taskflow_api.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUsuarioId(Long usuarioId);

    List<Tarea> findByEstado(Estado estado);

}
