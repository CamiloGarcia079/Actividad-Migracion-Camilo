package com.mindconnect.infrastructure.contact.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.contact.model.aggregates.Contact;

/**
 * Respuesta JSON de la API para un registro de contacts.
 */
public record ContactResponse(
        UUID id,
        String fullName,
        String email,
        String notes,
        UUID cityId,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID updatedBy) {

    public static ContactResponse from(Contact contact) {
        return new ContactResponse(
                contact.id(), contact.fullName(), contact.email(), contact.notes(), contact.cityId(), contact.createdAt(), contact.createdBy(), contact.updatedAt(), contact.updatedBy());
    }
}
