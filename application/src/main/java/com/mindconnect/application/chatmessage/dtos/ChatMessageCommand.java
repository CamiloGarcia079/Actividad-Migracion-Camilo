package com.mindconnect.application.chatmessage.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de chat_messages.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ChatMessageCommand(
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata) {
}
