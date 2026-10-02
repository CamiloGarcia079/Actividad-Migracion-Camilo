package com.mindconnect.application.patient.dtos;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de patients.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record PatientCommand(
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
        UUID createdBy,
        UUID updatedBy,
        UUID cityId) {
}
