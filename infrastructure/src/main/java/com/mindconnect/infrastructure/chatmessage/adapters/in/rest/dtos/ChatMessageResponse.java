package com.mindconnect.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;

/**
 * Respuesta JSON de la API para un registro de chat_messages.
 */
public record ChatMessageResponse(
        UUID id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata,
        LocalDateTime createdAt) {

    public static ChatMessageResponse from(ChatMessage chatMessage) {
        return new ChatMessageResponse(
                chatMessage.id(), chatMessage.conversationId(), chatMessage.messageTypeId(), chatMessage.participantId(), chatMessage.content(), chatMessage.metadata(), chatMessage.createdAt());
    }
}
