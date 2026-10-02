package com.mindconnect.domain.encountermodality.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar encounter_modalities.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface EncounterModalityRepository {

    EncounterModality save(EncounterModality encounterModality);

    Optional<EncounterModality> findById(UUID id);

    List<EncounterModality> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
