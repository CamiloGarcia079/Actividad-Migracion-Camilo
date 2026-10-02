package com.mindconnect.domain.priority.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla priorities.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Priority(
        UUID id,
        String namePriority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Priority {
        DomainValidations.required(id, "id");
        DomainValidations.required(namePriority, "namePriority");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
