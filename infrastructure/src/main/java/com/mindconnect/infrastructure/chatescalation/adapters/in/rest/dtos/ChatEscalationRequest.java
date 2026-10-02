package com.mindconnect.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatescalation.dtos.ChatEscalationCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_escalations.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatEscalationRequest(
        @NotNull UUID conversationId,
        @NotNull UUID statusId,
        @NotNull Boolean fromAi,
        @NotBlank String reason) {

    public ChatEscalationCommand toCommand() {
        return new ChatEscalationCommand(
                conversationId, statusId, fromAi, reason);
    }
}
