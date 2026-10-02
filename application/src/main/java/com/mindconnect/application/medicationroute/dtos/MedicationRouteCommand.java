package com.mindconnect.application.medicationroute.dtos;

/**
 * Datos que entran al caso de uso de medication_routes.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record MedicationRouteCommand(
        String code,
        String name,
        Boolean active) {
}
