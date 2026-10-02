package com.mindconnect.domain.patientcontact.model.aggregates;

import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla patient_contacts.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record PatientContact(
        UUID id,
        UUID contactId,
        UUID patientId,
        Boolean isPrimaryContact,
        Boolean isEmergencyContact,
        UUID relationshipTypeId) {

    public PatientContact {
        DomainValidations.required(id, "id");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(patientId, "patientId");
        DomainValidations.required(isPrimaryContact, "isPrimaryContact");
        DomainValidations.required(isEmergencyContact, "isEmergencyContact");
        DomainValidations.required(relationshipTypeId, "relationshipTypeId");
    }
}
