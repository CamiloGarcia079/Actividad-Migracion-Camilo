package com.mindconnect.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encounter.model.aggregates.Encounter;

/**
 * Respuesta JSON de la API para un registro de encounters.
 */
public record EncounterResponse(
        UUID id,
        UUID clinicalRecordId,
        UUID professionalId,
        UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy) {

    public static EncounterResponse from(Encounter encounter) {
        return new EncounterResponse(
                encounter.id(), encounter.clinicalRecordId(), encounter.professionalId(), encounter.encounterTypeId(), encounter.startedAt(), encounter.endedAt(), encounter.reasonForVisit(), encounter.currentCondition(), encounter.modalityId(), encounter.statusId(), encounter.createdAt(), encounter.createdBy(), encounter.updatedAt(), encounter.updatedBy());
    }
}
