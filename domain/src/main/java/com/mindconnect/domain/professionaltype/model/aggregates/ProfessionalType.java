package com.mindconnect.domain.professionaltype.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla professional_types.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ProfessionalType(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ProfessionalType {
        DomainValidations.required(id, "id");
        DomainValidations.required(name, "name");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
