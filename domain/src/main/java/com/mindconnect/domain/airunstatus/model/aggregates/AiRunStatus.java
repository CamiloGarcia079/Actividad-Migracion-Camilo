package com.mindconnect.domain.airunstatus.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla ai_runs_statuses.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record AiRunStatus(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public AiRunStatus {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameStatus, "nameStatus");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
