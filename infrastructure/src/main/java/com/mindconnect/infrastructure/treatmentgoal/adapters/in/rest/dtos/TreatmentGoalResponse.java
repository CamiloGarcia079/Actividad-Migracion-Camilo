package com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;

/**
 * Respuesta JSON de la API para un registro de treatment_goals.
 */
public record TreatmentGoalResponse(
        UUID id,
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static TreatmentGoalResponse from(TreatmentGoal treatmentGoal) {
        return new TreatmentGoalResponse(
                treatmentGoal.id(), treatmentGoal.treatmentPlanId(), treatmentGoal.description(), treatmentGoal.targetDate(), treatmentGoal.completedAt(), treatmentGoal.notes(), treatmentGoal.treatmentGoalId(), treatmentGoal.createdAt(), treatmentGoal.updatedAt());
    }
}
