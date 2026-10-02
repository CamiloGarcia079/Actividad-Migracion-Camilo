package com.mindconnect.application.chatescalationstatushistory.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_escalation_status_history.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatEscalationStatusHistoryCommand(
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime changedAt) {
}
