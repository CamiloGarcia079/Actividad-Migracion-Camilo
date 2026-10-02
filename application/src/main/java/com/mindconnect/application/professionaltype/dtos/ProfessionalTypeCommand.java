package com.mindconnect.application.professionaltype.dtos;

/**
 * Datos que entran al caso de uso de professional_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ProfessionalTypeCommand(
        String name) {
}
