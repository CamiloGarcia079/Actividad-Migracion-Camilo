package com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.treatmentgoal.dtos.TreatmentGoalCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de treatment_goals.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record TreatmentGoalRequest(
        @NotNull UUID treatmentPlanId,
        @NotBlank String description,
        @NotNull LocalDate targetDate,
        @NotNull LocalDateTime completedAt,
        @NotBlank String notes,
        @NotNull UUID treatmentGoalId) {

    public TreatmentGoalCommand toCommand() {
        return new TreatmentGoalCommand(
                treatmentPlanId, description, targetDate, completedAt, notes, treatmentGoalId);
    }
}
