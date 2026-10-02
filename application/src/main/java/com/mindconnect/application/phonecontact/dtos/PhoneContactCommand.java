package com.mindconnect.application.phonecontact.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de phone_contacts.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record PhoneContactCommand(
        UUID contactId,
        String phone,
        String notes) {
}
