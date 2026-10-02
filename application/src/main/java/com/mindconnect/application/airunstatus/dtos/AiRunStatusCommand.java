package com.mindconnect.application.airunstatus.dtos;

/**
 * Datos que entran al caso de uso de ai_runs_statuses.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record AiRunStatusCommand(
        String nameStatus) {
}
