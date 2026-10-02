package com.mindconnect.application.study.dtos;

/**
 * Datos que entran al caso de uso de studies.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record StudyCommand(
        String name) {
}
