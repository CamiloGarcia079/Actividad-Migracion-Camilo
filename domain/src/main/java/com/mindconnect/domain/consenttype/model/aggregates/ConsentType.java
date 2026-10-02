package com.mindconnect.domain.consenttype.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla consent_types.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ConsentType(
        UUID id,
        String code,
        String name,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ConsentType {
        DomainValidations.required(id, "id");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(active, "active");
        DomainValidations.required(description, "description");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
