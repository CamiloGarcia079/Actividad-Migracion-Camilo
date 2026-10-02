package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.treatmentgoalstatus.dtos.TreatmentGoalStatusCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de treatment_goal_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record TreatmentGoalStatusRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotNull Boolean active) {

    public TreatmentGoalStatusCommand toCommand() {
        return new TreatmentGoalStatusCommand(
                code, name, active);
    }
}
