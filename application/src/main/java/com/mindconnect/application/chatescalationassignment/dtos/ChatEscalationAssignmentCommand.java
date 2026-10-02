package com.mindconnect.application.chatescalationassignment.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_escalation_assignments.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatEscalationAssignmentCommand(
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt) {
}
