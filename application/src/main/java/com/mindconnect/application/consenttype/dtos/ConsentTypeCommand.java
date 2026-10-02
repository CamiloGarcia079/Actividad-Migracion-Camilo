package com.mindconnect.application.consenttype.dtos;

/**
 * Datos que entran al caso de uso de consent_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ConsentTypeCommand(
        String code,
        String name,
        Boolean active,
        String description) {
}
