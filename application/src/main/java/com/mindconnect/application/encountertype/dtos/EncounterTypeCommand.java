package com.mindconnect.application.encountertype.dtos;

/**
 * Datos que entran al caso de uso de encounter_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EncounterTypeCommand(
        String code,
        String name,
        Boolean active) {
}
