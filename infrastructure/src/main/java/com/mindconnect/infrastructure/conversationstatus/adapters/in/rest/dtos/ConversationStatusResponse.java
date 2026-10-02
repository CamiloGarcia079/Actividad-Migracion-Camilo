package com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;

/**
 * Respuesta JSON de la API para un registro de conversations_statuses.
 */
public record ConversationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ConversationStatusResponse from(ConversationStatus conversationStatus) {
        return new ConversationStatusResponse(
                conversationStatus.id(), conversationStatus.nameStatus(), conversationStatus.createdAt(), conversationStatus.updatedAt());
    }
}
