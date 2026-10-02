package com.mindconnect.domain.phonecontact.model.aggregates;

import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla phone_contacts.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record PhoneContact(
        UUID id,
        UUID contactId,
        String phone,
        String notes) {

    public PhoneContact {
        DomainValidations.required(id, "id");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(notes, "notes");
    }
}
