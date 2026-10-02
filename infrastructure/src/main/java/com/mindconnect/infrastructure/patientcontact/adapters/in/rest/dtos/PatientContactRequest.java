package com.mindconnect.infrastructure.patientcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import com.mindconnect.application.patientcontact.dtos.PatientContactCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de patient_contacts.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record PatientContactRequest(
        @NotNull UUID contactId,
        @NotNull UUID patientId,
        @NotNull Boolean isPrimaryContact,
        @NotNull Boolean isEmergencyContact,
        @NotNull UUID relationshipTypeId) {

    public PatientContactCommand toCommand() {
        return new PatientContactCommand(
                contactId, patientId, isPrimaryContact, isEmergencyContact, relationshipTypeId);
    }
}
