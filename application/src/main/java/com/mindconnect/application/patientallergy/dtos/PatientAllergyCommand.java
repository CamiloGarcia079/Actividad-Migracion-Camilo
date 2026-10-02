package com.mindconnect.application.patientallergy.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de patient_allergies.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record PatientAllergyCommand(
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        Boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy) {
}
