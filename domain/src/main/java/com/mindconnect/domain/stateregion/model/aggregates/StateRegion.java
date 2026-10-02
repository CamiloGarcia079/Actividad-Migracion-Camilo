package com.mindconnect.domain.stateregion.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla state_regions.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record StateRegion(
        UUID id,
        String nameRegion,
        String codeRegion,
        String description,
        Boolean isActive,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public StateRegion {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameRegion, "nameRegion");
        DomainValidations.required(codeRegion, "codeRegion");
        DomainValidations.required(description, "description");
        DomainValidations.required(isActive, "isActive");
        DomainValidations.required(countryId, "countryId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
