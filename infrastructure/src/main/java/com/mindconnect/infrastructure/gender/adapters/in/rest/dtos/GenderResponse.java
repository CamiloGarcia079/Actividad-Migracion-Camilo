package com.mindconnect.infrastructure.gender.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.gender.model.aggregates.Gender;

/**
 * Respuesta JSON de la API para un registro de genders.
 */
public record GenderResponse(
        UUID id,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static GenderResponse from(Gender gender) {
        return new GenderResponse(
                gender.id(), gender.description(), gender.createdAt(), gender.updatedAt());
    }
}
