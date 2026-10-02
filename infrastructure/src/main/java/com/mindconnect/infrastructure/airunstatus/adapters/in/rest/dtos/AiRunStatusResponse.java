package com.mindconnect.infrastructure.airunstatus.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;

/**
 * Respuesta JSON de la API para un registro de ai_runs_statuses.
 */
public record AiRunStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static AiRunStatusResponse from(AiRunStatus aiRunStatus) {
        return new AiRunStatusResponse(
                aiRunStatus.id(), aiRunStatus.nameStatus(), aiRunStatus.createdAt(), aiRunStatus.updatedAt());
    }
}
