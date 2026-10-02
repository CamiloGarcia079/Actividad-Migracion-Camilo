package com.mindconnect.application.clinicalnote.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de clinical_notes.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ClinicalNoteCommand(
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt) {
}
