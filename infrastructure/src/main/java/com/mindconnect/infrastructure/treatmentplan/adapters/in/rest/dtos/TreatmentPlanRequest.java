package com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.treatmentplan.dtos.TreatmentPlanCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de treatment_plans.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record TreatmentPlanRequest(
        @NotNull UUID encounterId,
        @NotNull UUID professionalId,
        @NotBlank @Size(max = 200) String title,
        @NotBlank String description,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull UUID treatmentStatusId) {

    public TreatmentPlanCommand toCommand() {
        return new TreatmentPlanCommand(
                encounterId, professionalId, title, description, startDate, endDate, treatmentStatusId);
    }
}
