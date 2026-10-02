package com.mindconnect.application.treatmentstatus.dtos;

/**
 * Datos que entran al caso de uso de treatment_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record TreatmentStatusCommand(
        String code,
        String name,
        Boolean active) {
}
