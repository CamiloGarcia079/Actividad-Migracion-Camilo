package com.mindconnect.domain.citymunicipality.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla city_municipalities.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record CityMunicipality(
        UUID id,
        String nameCity,
        String codeCity,
        String description,
        Boolean isActive,
        UUID regionId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public CityMunicipality {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameCity, "nameCity");
        DomainValidations.required(codeCity, "codeCity");
        DomainValidations.required(description, "description");
        DomainValidations.required(isActive, "isActive");
        DomainValidations.required(regionId, "regionId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
