package com.mindconnect.domain.patient.model.aggregates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla patients.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Patient(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String middleName,
        String lastName,
        String secondLastName,
        LocalDate birthDate,
        UUID biologicalSexId,
        UUID genderIdentity,
        String email,
        String phone,
        String address,
        Boolean active,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy,
        UUID cityId) {

    public Patient {
        DomainValidations.required(id, "id");
        DomainValidations.required(documentTypeId, "documentTypeId");
        DomainValidations.required(documentNumber, "documentNumber");
        DomainValidations.required(firstName, "firstName");
        DomainValidations.required(lastName, "lastName");
        DomainValidations.required(birthDate, "birthDate");
        DomainValidations.required(biologicalSexId, "biologicalSexId");
        DomainValidations.required(genderIdentity, "genderIdentity");
        DomainValidations.required(email, "email");
        DomainValidations.required(phone, "phone");
        DomainValidations.required(address, "address");
        DomainValidations.required(active, "active");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
        DomainValidations.required(cityId, "cityId");
    }
}
