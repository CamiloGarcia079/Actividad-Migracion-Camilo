package com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;

/**
 * Respuesta JSON de la API para un registro de chat_ai_run_errors.
 */
public record ChatAiRunErrorResponse(
        UUID id,
        UUID aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId,
        LocalDateTime createdAt) {

    public static ChatAiRunErrorResponse from(ChatAiRunError chatAiRunError) {
        return new ChatAiRunErrorResponse(
                chatAiRunError.id(), chatAiRunError.aiRunId(), chatAiRunError.errorMessage(), chatAiRunError.errorCode(), chatAiRunError.providerErrorId(), chatAiRunError.createdAt());
    }
}
