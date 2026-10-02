package com.mindconnect.infrastructure.patient.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.patient.model.aggregates.Patient;

/**
 * Respuesta JSON de la API para un registro de patients.
 */
public record PatientResponse(
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

    public static PatientResponse from(Patient patient) {
        return new PatientResponse(
                patient.id(), patient.documentTypeId(), patient.documentNumber(), patient.firstName(), patient.middleName(), patient.lastName(), patient.secondLastName(), patient.birthDate(), patient.biologicalSexId(), patient.genderIdentity(), patient.email(), patient.phone(), patient.address(), patient.active(), patient.createdAt(), patient.createdBy(), patient.updatedAt(), patient.updatedBy(), patient.cityId());
    }
}
