package com.mindconnect.domain.clinicalrecord.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla clinical_records.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ClinicalRecord(
        UUID id,
        UUID patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        UUID statusId,
        LocalDateTime createdAt,
        UUID createdBy) {

    public ClinicalRecord {
        DomainValidations.required(id, "id");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(creationDate, "creationDate");
        DomainValidations.required(recordNumber, "recordNumber");
        DomainValidations.required(openedAt, "openedAt");
        DomainValidations.required(closedAt, "closedAt");
        DomainValidations.required(statusId, "statusId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(createdBy, "createdBy");
    }
}
