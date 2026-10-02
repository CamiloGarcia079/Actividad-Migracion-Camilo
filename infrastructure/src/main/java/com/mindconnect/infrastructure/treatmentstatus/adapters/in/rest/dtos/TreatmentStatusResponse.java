package com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;

/**
 * Respuesta JSON de la API para un registro de treatment_statuses.
 */
public record TreatmentStatusResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static TreatmentStatusResponse from(TreatmentStatus treatmentStatus) {
        return new TreatmentStatusResponse(
                treatmentStatus.id(), treatmentStatus.code(), treatmentStatus.name(), treatmentStatus.active(), treatmentStatus.createdAt(), treatmentStatus.updatedAt());
    }
}
