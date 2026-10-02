package com.mindconnect.domain.clinicalnote.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla clinical_notes.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ClinicalNote(
        UUID id,
        UUID encounterId,
        UUID professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ClinicalNote {
        DomainValidations.required(id, "id");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(subjective, "subjective");
        DomainValidations.required(objective, "objective");
        DomainValidations.required(assessment, "assessment");
        DomainValidations.required(plan, "plan");
        DomainValidations.required(additionalNotes, "additionalNotes");
        DomainValidations.required(signedAt, "signedAt");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
