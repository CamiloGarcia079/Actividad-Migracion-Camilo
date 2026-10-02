package com.mindconnect.domain.chatescalationassignment.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_escalation_assignments.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatEscalationAssignment(
        UUID id,
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt) {

    public ChatEscalationAssignment {
        DomainValidations.required(id, "id");
        DomainValidations.required(escalationId, "escalationId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(assignedAt, "assignedAt");
    }
}
