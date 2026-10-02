package com.mindconnect.application.priority.dtos;

/**
 * Datos que entran al caso de uso de priorities.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record PriorityCommand(
        String namePriority) {
}
