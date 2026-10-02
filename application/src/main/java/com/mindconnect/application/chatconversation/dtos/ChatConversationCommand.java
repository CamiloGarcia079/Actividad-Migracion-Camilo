package com.mindconnect.application.chatconversation.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_conversations.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatConversationCommand(
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy) {
}
