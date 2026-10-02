package com.mindconnect.infrastructure.sendertype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.sendertype.model.aggregates.SenderType;

/**
 * Respuesta JSON de la API para un registro de sender_types.
 */
public record SenderTypeResponse(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static SenderTypeResponse from(SenderType senderType) {
        return new SenderTypeResponse(
                senderType.id(), senderType.nameType(), senderType.createdAt(), senderType.updatedAt());
    }
}
