package com.mindconnect.application.encountermodality.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encountermodality.dtos.EncounterModalityCommand;
import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre encounter_modalities.
 */
public interface EncounterModalityUseCase {

    EncounterModality create(EncounterModalityCommand command);

    EncounterModality getById(UUID id);

    List<EncounterModality> getAll();

    EncounterModality update(UUID id, EncounterModalityCommand command);

    void delete(UUID id);
}
