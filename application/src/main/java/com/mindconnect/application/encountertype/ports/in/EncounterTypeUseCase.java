package com.mindconnect.application.encountertype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encountertype.dtos.EncounterTypeCommand;
import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre encounter_types.
 */
public interface EncounterTypeUseCase {

    EncounterType create(EncounterTypeCommand command);

    EncounterType getById(UUID id);

    List<EncounterType> getAll();

    EncounterType update(UUID id, EncounterTypeCommand command);

    void delete(UUID id);
}
