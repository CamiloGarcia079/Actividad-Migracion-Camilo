package com.mindconnect.infrastructure.patientallergy.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.patientallergy.model.aggregates.PatientAllergy;

/**
 * Respuesta JSON de la API para un registro de patient_allergies.
 */
public record PatientAllergyResponse(
        UUID id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        Boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static PatientAllergyResponse from(PatientAllergy patientAllergy) {
        return new PatientAllergyResponse(
                patientAllergy.id(), patientAllergy.patientId(), patientAllergy.substance(), patientAllergy.reaction(), patientAllergy.severity(), patientAllergy.active(), patientAllergy.recordedAt(), patientAllergy.recordedBy(), patientAllergy.createdAt(), patientAllergy.updatedAt());
    }
}
