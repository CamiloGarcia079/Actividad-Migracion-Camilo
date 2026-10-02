package com.mindconnect.infrastructure.phonecontact.adapters.in.rest.dtos;

import java.util.UUID;

import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;

/**
 * Respuesta JSON de la API para un registro de phone_contacts.
 */
public record PhoneContactResponse(
        UUID id,
        UUID contactId,
        String phone,
        String notes) {

    public static PhoneContactResponse from(PhoneContact phoneContact) {
        return new PhoneContactResponse(
                phoneContact.id(), phoneContact.contactId(), phoneContact.phone(), phoneContact.notes());
    }
}
