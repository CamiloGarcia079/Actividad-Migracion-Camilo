package com.mindconnect.application.encounter.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encounter.dtos.EncounterCommand;
import com.mindconnect.domain.encounter.model.aggregates.Encounter;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre encounters.
 */
public interface EncounterUseCase {

    Encounter create(EncounterCommand command);

    Encounter getById(UUID id);

    List<Encounter> getAll();

    Encounter update(UUID id, EncounterCommand command);

    void delete(UUID id);
}
