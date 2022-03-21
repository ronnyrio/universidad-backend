package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import org.hibernate.sql.Delete;

import java.util.Optional;

public interface CarreraDAO {
    Optional<Carrera> findById(Integer Id);
    Carrera save(Carrera carrera);
    Iterable<Carrera> findAll();
    void deleteById(Integer Id);
}
