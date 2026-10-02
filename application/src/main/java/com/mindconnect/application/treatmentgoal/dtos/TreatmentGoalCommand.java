package com.mindconnect.application.treatmentgoal.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de treatment_goals.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record TreatmentGoalCommand(
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalId) {
}
