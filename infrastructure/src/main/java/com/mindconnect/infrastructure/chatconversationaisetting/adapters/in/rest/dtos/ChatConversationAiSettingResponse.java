package com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;

/**
 * Respuesta JSON de la API para un registro de chat_conversation_ai_settings.
 */
public record ChatConversationAiSettingResponse(
        UUID id,
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ChatConversationAiSettingResponse from(ChatConversationAiSetting chatConversationAiSetting) {
        return new ChatConversationAiSettingResponse(
                chatConversationAiSetting.id(), chatConversationAiSetting.conversationId(), chatConversationAiSetting.aiEnabled(), chatConversationAiSetting.defaultModelId(), chatConversationAiSetting.createdAt(), chatConversationAiSetting.updatedAt());
    }
}
