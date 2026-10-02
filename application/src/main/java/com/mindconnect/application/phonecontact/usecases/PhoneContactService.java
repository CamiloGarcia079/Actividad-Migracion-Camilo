package com.mindconnect.application.phonecontact.usecases;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.phonecontact.dtos.PhoneContactCommand;
import com.mindconnect.application.phonecontact.exceptions.PhoneContactNotFoundException;
import com.mindconnect.application.phonecontact.ports.in.PhoneContactUseCase;
import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;
import com.mindconnect.domain.phonecontact.repositories.PhoneContactRepository;

/**
 * Implementación de los casos de uso de phone_contacts.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class PhoneContactService implements PhoneContactUseCase {

    private final PhoneContactRepository repository;

    public PhoneContactService(PhoneContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneContact create(PhoneContactCommand command) {
        PhoneContact phoneContact = new PhoneContact(
                UUID.randomUUID(),
                command.contactId(),
                command.phone(),
                command.notes());
        return repository.save(phoneContact);
    }

    @Override
    public PhoneContact getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PhoneContactNotFoundException(id));
    }

    @Override
    public List<PhoneContact> getAll() {
        return repository.findAll();
    }

    @Override
    public PhoneContact update(UUID id, PhoneContactCommand command) {
        PhoneContact existing = getById(id);
        PhoneContact updated = new PhoneContact(
                existing.id(),
                command.contactId(),
                command.phone(),
                command.notes());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new PhoneContactNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
