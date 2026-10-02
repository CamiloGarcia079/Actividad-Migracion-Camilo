package com.mindconnect.application.chatairunerror.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_ai_run_errors.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatAiRunErrorCommand(
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId) {
}
