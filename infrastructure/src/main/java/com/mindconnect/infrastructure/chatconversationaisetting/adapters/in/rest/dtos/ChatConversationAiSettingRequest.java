package com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatconversationaisetting.dtos.ChatConversationAiSettingCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_conversation_ai_settings.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatConversationAiSettingRequest(
        @NotNull UUID conversationId,
        @NotNull Boolean aiEnabled,
        @NotNull UUID defaultModelId) {

    public ChatConversationAiSettingCommand toCommand() {
        return new ChatConversationAiSettingCommand(
                conversationId, aiEnabled, defaultModelId);
    }
}
