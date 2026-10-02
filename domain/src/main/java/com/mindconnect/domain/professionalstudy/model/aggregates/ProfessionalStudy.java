package com.mindconnect.domain.professionalstudy.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla professional_studies.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ProfessionalStudy(
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

    public ProfessionalStudy {
        DomainValidations.required(id, "id");
        DomainValidations.required(studyId, "studyId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(university, "university");
        DomainValidations.required(isValid, "isValid");
        DomainValidations.required(countryId, "countryId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
