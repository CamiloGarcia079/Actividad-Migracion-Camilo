package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;

/**
 * Respuesta JSON de la API para un registro de encounter_modalities.
 */
public record EncounterModalityResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EncounterModalityResponse from(EncounterModality encounterModality) {
        return new EncounterModalityResponse(
                encounterModality.id(), encounterModality.code(), encounterModality.name(), encounterModality.active(), encounterModality.createdAt(), encounterModality.updatedAt());
    }
}
