package com.mindconnect.application.chatconversationaisetting.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_conversation_ai_settings.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatConversationAiSettingCommand(
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId) {
}
