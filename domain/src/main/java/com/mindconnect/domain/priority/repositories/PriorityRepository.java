package com.mindconnect.domain.priority.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.priority.model.aggregates.Priority;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar priorities.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface PriorityRepository {

    Priority save(Priority priority);

    Optional<Priority> findById(UUID id);

    List<Priority> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
