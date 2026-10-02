package com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;

/**
 * Respuesta JSON de la API para un registro de assessment_types.
 */
public record AssessmentTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static AssessmentTypeResponse from(AssessmentType assessmentType) {
        return new AssessmentTypeResponse(
                assessmentType.id(), assessmentType.code(), assessmentType.name(), assessmentType.active(), assessmentType.description(), assessmentType.createdAt(), assessmentType.updatedAt());
    }
}
