package com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;

/**
 * Respuesta JSON de la API para un registro de encounter_statuses.
 */
public record EncounterStatusResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EncounterStatusResponse from(EncounterStatus encounterStatus) {
        return new EncounterStatusResponse(
                encounterStatus.id(), encounterStatus.code(), encounterStatus.name(), encounterStatus.active(), encounterStatus.createdAt(), encounterStatus.updatedAt());
    }
}
