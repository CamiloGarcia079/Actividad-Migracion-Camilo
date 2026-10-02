package com.mindconnect.domain.gender.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla genders.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Gender(
        UUID id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Gender {
        DomainValidations.required(id, "id");
        DomainValidations.required(description, "description");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
