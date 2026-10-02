package com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;

/**
 * Respuesta JSON de la API para un registro de escalations_statuses.
 */
public record EscalationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EscalationStatusResponse from(EscalationStatus escalationStatus) {
        return new EscalationStatusResponse(
                escalationStatus.id(), escalationStatus.nameStatus(), escalationStatus.createdAt(), escalationStatus.updatedAt());
    }
}
