package com.mindconnect.application.emailcontact.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.emailcontact.dtos.EmailContactCommand;
import com.mindconnect.application.emailcontact.exceptions.EmailContactNotFoundException;
import com.mindconnect.application.emailcontact.ports.in.EmailContactUseCase;
import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;
import com.mindconnect.domain.emailcontact.repositories.EmailContactRepository;

/**
 * Implementación de los casos de uso de email_contacts.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EmailContactService implements EmailContactUseCase {

    private final EmailContactRepository repository;

    public EmailContactService(EmailContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmailContact create(EmailContactCommand command) {
        LocalDateTime now = LocalDateTime.now();
        EmailContact emailContact = new EmailContact(
                UUID.randomUUID(),
                command.contactId(),
                command.email(),
                command.notes(),
                now,
                now);
        return repository.save(emailContact);
    }

    @Override
    public EmailContact getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmailContactNotFoundException(id));
    }

    @Override
    public List<EmailContact> getAll() {
        return repository.findAll();
    }

    @Override
    public EmailContact update(UUID id, EmailContactCommand command) {
        EmailContact existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        EmailContact updated = new EmailContact(
                existing.id(),
                command.contactId(),
                command.email(),
                command.notes(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EmailContactNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
