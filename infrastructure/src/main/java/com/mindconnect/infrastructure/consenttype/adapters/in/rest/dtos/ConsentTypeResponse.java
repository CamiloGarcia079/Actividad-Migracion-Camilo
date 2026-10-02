package com.mindconnect.infrastructure.consenttype.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;

/**
 * Respuesta JSON de la API para un registro de consent_types.
 */
public record ConsentTypeResponse(
        UUID id,
        String code,
        String name,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ConsentTypeResponse from(ConsentType consentType) {
        return new ConsentTypeResponse(
                consentType.id(), consentType.code(), consentType.name(), consentType.active(), consentType.description(), consentType.createdAt(), consentType.updatedAt());
    }
}
