package com.mindconnect.application.encountermodality.dtos;

/**
 * Datos que entran al caso de uso de encounter_modalities.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EncounterModalityCommand(
        String code,
        String name,
        Boolean active) {
}
