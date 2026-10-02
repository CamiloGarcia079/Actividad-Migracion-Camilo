package com.mindconnect.application.patientcontact.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de patient_contacts.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record PatientContactCommand(
        UUID contactId,
        UUID patientId,
        Boolean isPrimaryContact,
        Boolean isEmergencyContact,
        UUID relationshipTypeId) {
}
