package com.mindconnect.application.treatmentgoalstatus.dtos;

/**
 * Datos que entran al caso de uso de treatment_goal_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record TreatmentGoalStatusCommand(
        String code,
        String name,
        Boolean active) {
}
