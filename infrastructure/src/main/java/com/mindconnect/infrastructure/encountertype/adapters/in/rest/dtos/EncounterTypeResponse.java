package com.mindconnect.infrastructure.encountertype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;

/**
 * Respuesta JSON de la API para un registro de encounter_types.
 */
public record EncounterTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EncounterTypeResponse from(EncounterType encounterType) {
        return new EncounterTypeResponse(
                encounterType.id(), encounterType.code(), encounterType.name(), encounterType.active(), encounterType.createdAt(), encounterType.updatedAt());
    }
}
