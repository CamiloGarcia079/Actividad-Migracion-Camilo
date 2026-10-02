package com.mindconnect.domain.encountertype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar encounter_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EncounterTypeRepository {

    EncounterType save(EncounterType encounterType);

    Optional<EncounterType> findById(UUID id);

    List<EncounterType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
