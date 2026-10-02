package com.mindconnect.domain.country.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla countries.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Country(
        UUID id,
        String nameCountry,
        String codeCountry,
        String description,
        Boolean isActive,
        String telephonePrefix,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Country {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameCountry, "nameCountry");
        DomainValidations.required(codeCountry, "codeCountry");
        DomainValidations.required(description, "description");
        DomainValidations.required(isActive, "isActive");
        DomainValidations.required(telephonePrefix, "telephonePrefix");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
