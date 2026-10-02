package com.mindconnect.infrastructure.messagetype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.messagetype.model.aggregates.MessageType;

/**
 * Respuesta JSON de la API para un registro de message_types.
 */
public record MessageTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static MessageTypeResponse from(MessageType messageType) {
        return new MessageTypeResponse(
                messageType.id(), messageType.nameType(), messageType.createdAt(), messageType.updatedAt());
    }
}
