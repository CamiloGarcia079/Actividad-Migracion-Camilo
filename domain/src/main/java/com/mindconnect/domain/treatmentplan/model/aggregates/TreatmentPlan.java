package com.mindconnect.domain.treatmentplan.model.aggregates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla treatment_plans.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record TreatmentPlan(
        UUID id,
        UUID encounterId,
        UUID professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        UUID treatmentStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public TreatmentPlan {
        DomainValidations.required(id, "id");
        DomainValidations.required(encounterId, "encounterId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(title, "title");
        DomainValidations.required(description, "description");
        DomainValidations.required(startDate, "startDate");
        DomainValidations.required(endDate, "endDate");
        DomainValidations.required(treatmentStatusId, "treatmentStatusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
