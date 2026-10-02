package com.mindconnect.infrastructure.chatairun.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatairun.dtos.ChatAiRunCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_ai_runs.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatAiRunRequest(
        @NotNull UUID conversationId,
        @NotNull UUID messageId,
        @NotNull UUID modelId,
        @NotNull UUID aiRunStatusId) {

    public ChatAiRunCommand toCommand() {
        return new ChatAiRunCommand(
                conversationId, messageId, modelId, aiRunStatusId);
    }
}
