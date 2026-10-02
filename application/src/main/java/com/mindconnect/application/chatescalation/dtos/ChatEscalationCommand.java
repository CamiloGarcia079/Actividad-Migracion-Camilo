package com.mindconnect.application.chatescalation.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_escalations.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatEscalationCommand(
        UUID conversationId,
        UUID statusId,
        Boolean fromAi,
        String reason) {
}
