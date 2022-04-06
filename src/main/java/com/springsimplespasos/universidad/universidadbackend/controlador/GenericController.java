package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class GenericController <E, S extends GenericDAO<E>> {

    protected final S service;
    public String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public List<E> obtenerTodos(){
        List<E> listado = (List<E>) service.findAll();
        if (listado.isEmpty()) {
            throw new BadRequestException(String.format("No se han encuentradpo %ss", nombreEntidad));
        }
        return listado;
    }

    @PostMapping
    public Persona altaAlumno(@RequestBody E alumno) {
        return service.save(alumno);
    }

    //obtener por id (id)

    //borrar entidad por id (id)

    //alta entidad (Entidad)

}
