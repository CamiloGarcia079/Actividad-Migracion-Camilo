package com.mindconnect.infrastructure.priority.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.priority.model.aggregates.Priority;

/**
 * Respuesta JSON de la API para un registro de priorities.
 */
public record PriorityResponse(
        UUID id,
        String namePriority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static PriorityResponse from(Priority priority) {
        return new PriorityResponse(
                priority.id(), priority.namePriority(), priority.createdAt(), priority.updatedAt());
    }
}
