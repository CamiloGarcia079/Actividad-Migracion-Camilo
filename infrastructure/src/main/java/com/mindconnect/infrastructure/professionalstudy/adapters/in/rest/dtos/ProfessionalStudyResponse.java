package com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;

/**
 * Respuesta JSON de la API para un registro de professional_studies.
 */
public record ProfessionalStudyResponse(
        UUID id,
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        Boolean isValid,
        String resolutionNumber,
        UUID countryId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static ProfessionalStudyResponse from(ProfessionalStudy professionalStudy) {
        return new ProfessionalStudyResponse(
                professionalStudy.id(), professionalStudy.studyId(), professionalStudy.professionalId(), professionalStudy.title(), professionalStudy.university(), professionalStudy.isValid(), professionalStudy.resolutionNumber(), professionalStudy.countryId(), professionalStudy.createdAt(), professionalStudy.updatedAt());
    }
}
