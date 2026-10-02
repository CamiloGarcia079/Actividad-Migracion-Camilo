package com.mindconnect.application.professional.dtos;

import java.util.UUID;

/**
 * Datos que entran al caso de uso de professionals.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record ProfessionalCommand(
        UUID documentTypeId,
        String documentNumber,
        String firstName,
        String lastName,
        UUID professionalType,
        String licenseNumber,
        Boolean active,
        UUID cityId) {
}
