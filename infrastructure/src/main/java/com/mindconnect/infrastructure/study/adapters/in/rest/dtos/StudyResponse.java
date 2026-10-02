package com.mindconnect.infrastructure.study.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.study.model.aggregates.Study;

/**
 * Respuesta JSON de la API para un registro de studies.
 */
public record StudyResponse(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static StudyResponse from(Study study) {
        return new StudyResponse(
                study.id(), study.name(), study.createdAt(), study.updatedAt());
    }
}
