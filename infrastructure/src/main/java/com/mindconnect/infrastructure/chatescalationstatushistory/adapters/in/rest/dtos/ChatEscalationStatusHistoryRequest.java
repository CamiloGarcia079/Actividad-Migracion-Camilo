package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatescalationstatushistory.dtos.ChatEscalationStatusHistoryCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_escalation_status_history.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatEscalationStatusHistoryRequest(
        @NotNull UUID escalationId,
        @NotNull UUID escalationStatusId,
        @NotNull LocalDateTime changedAt) {

    public ChatEscalationStatusHistoryCommand toCommand() {
        return new ChatEscalationStatusHistoryCommand(
                escalationId, escalationStatusId, changedAt);
    }
}
