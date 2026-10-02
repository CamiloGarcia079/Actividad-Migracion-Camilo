package com.mindconnect.infrastructure.emailcontact.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;

/**
 * Respuesta JSON de la API para un registro de email_contacts.
 */
public record EmailContactResponse(
        UUID id,
        UUID contactId,
        String email,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static EmailContactResponse from(EmailContact emailContact) {
        return new EmailContactResponse(
                emailContact.id(), emailContact.contactId(), emailContact.email(), emailContact.notes(), emailContact.createdAt(), emailContact.updatedAt());
    }
}
