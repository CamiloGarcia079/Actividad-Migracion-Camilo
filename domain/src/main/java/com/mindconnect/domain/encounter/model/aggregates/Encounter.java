package com.mindconnect.domain.encounter.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla encounters.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record Encounter(
        UUID id,
        UUID clinicalRecordId,
        UUID professionalId,
        UUID encounterTypeId,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        String reasonForVisit,
        String currentCondition,
        UUID modalityId,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy) {

    public Encounter {
        DomainValidations.required(id, "id");
        DomainValidations.required(clinicalRecordId, "clinicalRecordId");
        DomainValidations.required(professionalId, "professionalId");
        DomainValidations.required(encounterTypeId, "encounterTypeId");
        DomainValidations.required(startedAt, "startedAt");
        DomainValidations.required(endedAt, "endedAt");
        DomainValidations.required(reasonForVisit, "reasonForVisit");
        DomainValidations.required(currentCondition, "currentCondition");
        DomainValidations.required(modalityId, "modalityId");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
        DomainValidations.required(updatedAt, "updatedAt");
        DomainValidations.required(updatedBy, "updatedBy");
    }
}
