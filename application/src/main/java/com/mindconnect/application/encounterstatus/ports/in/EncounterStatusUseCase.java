package com.mindconnect.application.encounterstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encounterstatus.dtos.EncounterStatusCommand;
import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre encounter_statuses.
 */
public interface EncounterStatusUseCase {

    EncounterStatus create(EncounterStatusCommand command);

    EncounterStatus getById(UUID id);

    List<EncounterStatus> getAll();

    EncounterStatus update(UUID id, EncounterStatusCommand command);

    void delete(UUID id);
}
