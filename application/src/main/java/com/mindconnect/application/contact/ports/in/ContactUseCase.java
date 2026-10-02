package com.mindconnect.application.contact.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.contact.dtos.ContactCommand;
import com.mindconnect.domain.contact.model.aggregates.Contact;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre contacts.
 */
public interface ContactUseCase {

    Contact create(ContactCommand command);

    Contact getById(UUID id);

    List<Contact> getAll();

    Contact update(UUID id, ContactCommand command);

    void delete(UUID id);
}
