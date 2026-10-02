package com.mindconnect.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;

import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;

/**
 * Respuesta JSON de la API para un registro de patient_contacts.
 */
public record PatientContactResponse(
        UUID id,
        UUID contactId,
        UUID patientId,
        Boolean isPrimaryContact,
        Boolean isEmergencyContact,
        UUID relationshipTypeId) {

    public static PatientContactResponse from(PatientContact patientContact) {
        return new PatientContactResponse(
                patientContact.id(), patientContact.contactId(), patientContact.patientId(), patientContact.isPrimaryContact(), patientContact.isEmergencyContact(), patientContact.relationshipTypeId());
    }
}
