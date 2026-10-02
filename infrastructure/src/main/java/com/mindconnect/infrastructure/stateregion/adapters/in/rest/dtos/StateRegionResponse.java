package com.mindconnect.infrastructure.stateregion.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;

/**
 * Respuesta JSON de la API para un registro de state_regions.
 */
public record StateRegionResponse(
        UUID id,
        String nameRegion,
        String codeRegion,
        String description,
        Boolean isActive,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static StateRegionResponse from(StateRegion stateRegion) {
        return new StateRegionResponse(
                stateRegion.id(), stateRegion.nameRegion(), stateRegion.codeRegion(), stateRegion.description(), stateRegion.isActive(), stateRegion.countryId(), stateRegion.createdAt(), stateRegion.updatedAt());
    }
}
