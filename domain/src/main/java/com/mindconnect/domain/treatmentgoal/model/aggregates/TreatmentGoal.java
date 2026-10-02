package com.mindconnect.domain.treatmentgoal.model.aggregates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla treatment_goals.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record TreatmentGoal(
        UUID id,
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public TreatmentGoal {
        DomainValidations.required(id, "id");
        DomainValidations.required(treatmentPlanId, "treatmentPlanId");
        DomainValidations.required(description, "description");
        DomainValidations.required(targetDate, "targetDate");
        DomainValidations.required(completedAt, "completedAt");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(treatmentGoalId, "treatmentGoalId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
