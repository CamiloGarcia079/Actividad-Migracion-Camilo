package com.mindconnect.infrastructure.professionaltype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;

/**
 * Respuesta JSON de la API para un registro de professional_types.
 */
public record ProfessionalTypeResponse(
        UUID id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ProfessionalTypeResponse from(ProfessionalType professionalType) {
        return new ProfessionalTypeResponse(
                professionalType.id(), professionalType.name(), professionalType.createdAt(), professionalType.updatedAt());
    }
}
