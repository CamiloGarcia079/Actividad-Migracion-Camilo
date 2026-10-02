package com.mindconnect.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;

/**
 * Respuesta JSON de la API para un registro de chat_escalations.
 */
public record ChatEscalationResponse(
        UUID id,
        UUID conversationId,
        UUID statusId,
        Boolean fromAi,
        String reason,
        LocalDateTime createdAt) {

    public static ChatEscalationResponse from(ChatEscalation chatEscalation) {
        return new ChatEscalationResponse(
                chatEscalation.id(), chatEscalation.conversationId(), chatEscalation.statusId(), chatEscalation.fromAi(), chatEscalation.reason(), chatEscalation.createdAt());
    }
}
