package com.mindconnect.application.professionalstudy.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de professional_studies.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ProfessionalStudyCommand(
        UUID studyId,
        UUID professionalId,
        String title,
        String university,
        Boolean isValid,
        String resolutionNumber,
        UUID countryId) {
}
