package com.mindconnect.infrastructure.medicationroute.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;

/**
 * Respuesta JSON de la API para un registro de medication_routes.
 */
public record MedicationRouteResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static MedicationRouteResponse from(MedicationRoute medicationRoute) {
        return new MedicationRouteResponse(
                medicationRoute.id(), medicationRoute.code(), medicationRoute.name(), medicationRoute.active(), medicationRoute.createdAt(), medicationRoute.updatedAt());
    }
}
