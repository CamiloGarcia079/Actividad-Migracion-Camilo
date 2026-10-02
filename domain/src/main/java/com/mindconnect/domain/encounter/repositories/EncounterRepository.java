package com.mindconnect.domain.encounter.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.encounter.model.aggregates.Encounter;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar encounters.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EncounterRepository {

    Encounter save(Encounter encounter);

    Optional<Encounter> findById(UUID id);

    List<Encounter> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
