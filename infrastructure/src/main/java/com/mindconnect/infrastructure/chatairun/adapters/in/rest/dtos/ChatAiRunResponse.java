package com.mindconnect.infrastructure.chatairun.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;

/**
 * Respuesta JSON de la API para un registro de chat_ai_runs.
 */
public record ChatAiRunResponse(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ChatAiRunResponse from(ChatAiRun chatAiRun) {
        return new ChatAiRunResponse(
                chatAiRun.id(), chatAiRun.conversationId(), chatAiRun.messageId(), chatAiRun.modelId(), chatAiRun.aiRunStatusId(), chatAiRun.createdAt(), chatAiRun.updatedAt());
    }
}
