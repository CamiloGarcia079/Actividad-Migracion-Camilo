package com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;

/**
 * Respuesta JSON de la API para un registro de treatment_plans.
 */
public record TreatmentPlanResponse(
        UUID id,
        UUID encounterId,
        UUID professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        UUID treatmentStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static TreatmentPlanResponse from(TreatmentPlan treatmentPlan) {
        return new TreatmentPlanResponse(
                treatmentPlan.id(), treatmentPlan.encounterId(), treatmentPlan.professionalId(), treatmentPlan.title(), treatmentPlan.description(), treatmentPlan.startDate(), treatmentPlan.endDate(), treatmentPlan.treatmentStatusId(), treatmentPlan.createdAt(), treatmentPlan.updatedAt());
    }
}
