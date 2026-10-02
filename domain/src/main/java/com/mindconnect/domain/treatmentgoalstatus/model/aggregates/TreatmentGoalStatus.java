package com.mindconnect.domain.treatmentgoalstatus.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla treatment_goal_statuses.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record TreatmentGoalStatus(
        UUID id,
        String code,
        String name,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public TreatmentGoalStatus {
        DomainValidations.required(id, "id");
        DomainValidations.required(code, "code");
        DomainValidations.required(name, "name");
        DomainValidations.required(active, "active");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
