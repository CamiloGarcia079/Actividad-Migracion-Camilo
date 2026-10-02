package com.mindconnect.domain.study.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.study.model.aggregates.Study;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar studies.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface StudyRepository {

    Study save(Study study);

    Optional<Study> findById(UUID id);

    List<Study> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
