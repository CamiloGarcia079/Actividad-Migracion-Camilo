package com.mindconnect.application.treatmentplan.dtos;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de treatment_plans.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record TreatmentPlanCommand(
        UUID encounterId,
        UUID professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        UUID treatmentStatusId) {
}
