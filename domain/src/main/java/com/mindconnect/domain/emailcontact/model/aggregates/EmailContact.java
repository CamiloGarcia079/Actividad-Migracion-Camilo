package com.mindconnect.domain.emailcontact.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla email_contacts.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record EmailContact(
        UUID id,
        UUID contactId,
        String email,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public EmailContact {
        DomainValidations.required(id, "id");
        DomainValidations.required(contactId, "contactId");
        DomainValidations.required(email, "email");
        DomainValidations.required(notes, "notes");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
