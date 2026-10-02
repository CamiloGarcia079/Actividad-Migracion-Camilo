package com.mindconnect.application.assessmenttype.dtos;

/**
 * Datos que entran al caso de uso de assessment_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record AssessmentTypeCommand(
        String code,
        String name,
        Boolean active,
        String description) {
}
