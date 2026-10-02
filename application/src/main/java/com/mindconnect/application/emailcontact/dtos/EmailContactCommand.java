package com.mindconnect.application.emailcontact.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de email_contacts.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record EmailContactCommand(
        UUID contactId,
        String email,
        String notes) {
}
