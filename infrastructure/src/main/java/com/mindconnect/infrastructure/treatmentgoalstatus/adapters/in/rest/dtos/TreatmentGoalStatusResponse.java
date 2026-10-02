package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;

/**
 * Respuesta JSON de la API para un registro de treatment_goal_statuses.
 */
public record TreatmentGoalStatusResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static TreatmentGoalStatusResponse from(TreatmentGoalStatus treatmentGoalStatus) {
        return new TreatmentGoalStatusResponse(
                treatmentGoalStatus.id(), treatmentGoalStatus.code(), treatmentGoalStatus.name(), treatmentGoalStatus.active(), treatmentGoalStatus.createdAt(), treatmentGoalStatus.updatedAt());
    }
}
