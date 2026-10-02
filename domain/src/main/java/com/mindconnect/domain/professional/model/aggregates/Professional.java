package com.mindconnect.domain.professional.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla professionals.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Professional(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        Boolean active,
        UUID cityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Professional {
        DomainValidations.required(id, "id");
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(professionalType, "professionalType");
        DomainValidations.required(licenseNumber, "licenseNumber");
        DomainValidations.required(active, "active");
        DomainValidations.required(cityId, "cityId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
