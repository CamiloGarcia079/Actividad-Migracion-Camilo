package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;

/**
 * Respuesta JSON de la API para un registro de chat_escalation_status_history.
 */
public record ChatEscalationStatusHistoryResponse(
        UUID id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime createdAt,
        LocalDateTime changedAt) {

    public static ChatEscalationStatusHistoryResponse from(ChatEscalationStatusHistory chatEscalationStatusHistory) {
        return new ChatEscalationStatusHistoryResponse(
                chatEscalationStatusHistory.id(), chatEscalationStatusHistory.escalationId(), chatEscalationStatusHistory.escalationStatusId(), chatEscalationStatusHistory.createdAt(), chatEscalationStatusHistory.changedAt());
    }
}
