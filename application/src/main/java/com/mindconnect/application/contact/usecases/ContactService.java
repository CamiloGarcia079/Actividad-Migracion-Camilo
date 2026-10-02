package com.mindconnect.application.contact.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.contact.dtos.ContactCommand;
import com.mindconnect.application.contact.exceptions.ContactNotFoundException;
import com.mindconnect.application.contact.ports.in.ContactUseCase;
import com.mindconnect.domain.contact.model.aggregates.Contact;
import com.mindconnect.domain.contact.repositories.ContactRepository;

/**
 * Implementación de los casos de uso de contacts.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ContactService implements ContactUseCase {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact create(ContactCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Contact contact = new Contact(
                UUID.randomUUID(),
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                now,
                command.createdBy(),
                now,
                command.updatedBy());
        return repository.save(contact);
    }

    @Override
    public Contact getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @Override
    public List<Contact> getAll() {
        return repository.findAll();
    }

    @Override
    public Contact update(UUID id, ContactCommand command) {
        Contact existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Contact updated = new Contact(
                existing.id(),
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                existing.createdAt(),
                command.createdBy(),
                now,
                command.updatedBy());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
