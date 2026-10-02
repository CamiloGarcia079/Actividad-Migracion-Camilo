package com.mindconnect.application.gender.dtos;

/**
 * Datos que entran al caso de uso de genders.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record GenderCommand(
        String description) {
}
