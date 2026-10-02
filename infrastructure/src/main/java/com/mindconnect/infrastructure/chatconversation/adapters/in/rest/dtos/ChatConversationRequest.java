package com.mindconnect.infrastructure.chatconversation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatconversation.dtos.ChatConversationCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_conversations.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatConversationRequest(
        @NotNull UUID conversationStatusId,
        @NotNull UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy) {

    public ChatConversationCommand toCommand() {
        return new ChatConversationCommand(
                conversationStatusId, priorityId, lastMessageAt, closed, closedAt, closedBy);
    }
}
