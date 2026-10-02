package com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.chatescalationassignment.dtos.ChatEscalationAssignmentCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de chat_escalation_assignments.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record ChatEscalationAssignmentRequest(
        @NotNull UUID escalationId,
        @NotNull UUID professionalId,
        @NotNull LocalDateTime assignedAt) {

    public ChatEscalationAssignmentCommand toCommand() {
        return new ChatEscalationAssignmentCommand(
                escalationId, professionalId, assignedAt);
    }
}
