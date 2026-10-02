package com.mindconnect.application.escalationstatus.dtos;

/**
 * Datos que entran al caso de uso de escalations_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EscalationStatusCommand(
        String nameStatus) {
}
