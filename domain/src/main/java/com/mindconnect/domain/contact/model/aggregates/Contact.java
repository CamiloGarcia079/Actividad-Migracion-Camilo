package com.mindconnect.domain.contact.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla contacts.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Contact(
        UUID id,
        String fullName,
        String email,
        String notes,
        UUID cityId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy) {

    public Contact {
        DomainValidations.required(id, "id");
        DomainValidations.required(fullName, "fullName");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(cityId, "cityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
