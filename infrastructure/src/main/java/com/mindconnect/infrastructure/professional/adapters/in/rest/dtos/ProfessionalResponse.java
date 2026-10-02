package com.mindconnect.infrastructure.professional.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professional.model.aggregates.Professional;

/**
 * Respuesta JSON de la API para un registro de professionals.
 */
public record ProfessionalResponse(
        UUID id,
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        Boolean active,
        UUID cityId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ProfessionalResponse from(Professional professional) {
        return new ProfessionalResponse(
                professional.id(), professional.documentTypeId(), professional.documentNumber(), professional.firstName(), professional.lastName(), professional.professionalType(), professional.licenseNumber(), professional.active(), professional.cityId(), professional.createdAt(), professional.updatedAt());
    }
}
