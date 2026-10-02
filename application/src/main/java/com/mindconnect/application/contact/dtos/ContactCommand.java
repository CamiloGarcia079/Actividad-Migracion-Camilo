package com.mindconnect.application.contact.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de contacts.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ContactCommand(
        String fullName,
        String email,
        String notes,
        UUID cityId,
        UUID createdBy,
        UUID updatedBy) {
}
