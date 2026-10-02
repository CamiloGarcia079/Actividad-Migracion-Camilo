package com.mindconnect.domain.patientallergy.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla patient_allergies.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record PatientAllergy(
        UUID id,
        UUID patientId,
        String substance,
        String reaction,
        String severity,
        Boolean active,
        LocalDateTime recordedAt,
        UUID recordedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public PatientAllergy {
        DomainValidations.required(id, "id");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(substance, "substance");
        DomainValidations.required(severity, "severity");
        DomainValidations.required(active, "active");
        DomainValidations.required(recordedAt, "recordedAt");
        DomainValidations.required(recordedBy, "recordedBy");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
