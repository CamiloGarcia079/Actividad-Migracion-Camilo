package com.mindconnect.application.emailcontact.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.emailcontact.dtos.EmailContactCommand;
import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre email_contacts.
 */
public interface EmailContactUseCase {

    EmailContact create(EmailContactCommand command);

    EmailContact getById(UUID id);

    List<EmailContact> getAll();

    EmailContact update(UUID id, EmailContactCommand command);

    void delete(UUID id);
}
