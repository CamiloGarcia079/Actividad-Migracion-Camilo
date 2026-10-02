package com.mindconnect.application.encounterstatus.dtos;

/**
 * Datos que entran al caso de uso de encounter_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EncounterStatusCommand(
        String code,
        String name,
        Boolean active) {
}
