package com.mindconnect.application.chatairun.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_ai_runs.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatAiRunCommand(
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId) {
}
