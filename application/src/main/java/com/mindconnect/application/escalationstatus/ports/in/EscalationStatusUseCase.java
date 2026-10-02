package com.mindconnect.application.escalationstatus.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.escalationstatus.dtos.EscalationStatusCommand;
import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre escalations_statuses.
 */
public interface EscalationStatusUseCase {

    EscalationStatus create(EscalationStatusCommand command);

    EscalationStatus getById(UUID id);

    List<EscalationStatus> getAll();

    EscalationStatus update(UUID id, EscalationStatusCommand command);

    void delete(UUID id);
}
