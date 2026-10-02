package com.mindconnect.infrastructure.chatconversation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;

/**
 * Respuesta JSON de la API para un registro de chat_conversations.
 */
public record ChatConversationResponse(
        UUID id,
        UUID conversationStatusId,
        UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ChatConversationResponse from(ChatConversation chatConversation) {
        return new ChatConversationResponse(
                chatConversation.id(), chatConversation.conversationStatusId(), chatConversation.priorityId(), chatConversation.lastMessageAt(), chatConversation.closed(), chatConversation.closedAt(), chatConversation.closedBy(), chatConversation.createdAt(), chatConversation.updatedAt());
    }
}
