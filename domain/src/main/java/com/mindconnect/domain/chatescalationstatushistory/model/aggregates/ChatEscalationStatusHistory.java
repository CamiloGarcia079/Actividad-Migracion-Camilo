package com.mindconnect.domain.chatescalationstatushistory.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_escalation_status_history.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatEscalationStatusHistory(
        UUID id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime createdAt,
        LocalDateTime changedAt) {

    public ChatEscalationStatusHistory {
        DomainValidations.required(id, "id");
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(escalationStatusId, "escalationStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(changedAt, "changedAt");
    }
}
