package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import java.util.Optional;

public interface GenericDAO <E>{
    Optional<E> findById(Integer id);
    E save(E entidad);
    Iterable<E> findAll();
    void deletedById(Integer id);
}
