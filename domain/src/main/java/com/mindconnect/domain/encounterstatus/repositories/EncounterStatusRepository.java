package com.mindconnect.domain.encounterstatus.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar encounter_statuses.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EncounterStatusRepository {

    EncounterStatus save(EncounterStatus encounterStatus);

    Optional<EncounterStatus> findById(UUID id);

    List<EncounterStatus> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
