package com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;

/**
 * Respuesta JSON de la API para un registro de chat_escalation_assignments.
 */
public record ChatEscalationAssignmentResponse(
        UUID id,
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt) {

    public static ChatEscalationAssignmentResponse from(ChatEscalationAssignment chatEscalationAssignment) {
        return new ChatEscalationAssignmentResponse(
                chatEscalationAssignment.id(), chatEscalationAssignment.escalationId(), chatEscalationAssignment.professionalId(), chatEscalationAssignment.assignedAt());
    }
}
