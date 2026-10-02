package com.mindconnect.application.encounter.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de encounters.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EncounterCommand(
        UUID clinicalRecordId,
        UUID professionalId,
        UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        UUID createdBy,
        UUID updatedBy) {
}
