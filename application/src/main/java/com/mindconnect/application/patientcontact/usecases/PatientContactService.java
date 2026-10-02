package com.mindconnect.application.patientcontact.usecases;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.patientcontact.dtos.PatientContactCommand;
import com.mindconnect.application.patientcontact.exceptions.PatientContactNotFoundException;
import com.mindconnect.application.patientcontact.ports.in.PatientContactUseCase;
import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;
import com.mindconnect.domain.patientcontact.repositories.PatientContactRepository;

/**
 * Implementación de los casos de uso de patient_contacts.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class PatientContactService implements PatientContactUseCase {

    private final PatientContactRepository repository;

    public PatientContactService(PatientContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public PatientContact create(PatientContactCommand command) {
        PatientContact patientContact = new PatientContact(
                UUID.randomUUID(),
                command.contactId(),
                command.patientId(),
                command.isPrimaryContact(),
                command.isEmergencyContact(),
                command.relationshipTypeId());
        return repository.save(patientContact);
    }

    @Override
    public PatientContact getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PatientContactNotFoundException(id));
    }

    @Override
    public List<PatientContact> getAll() {
        return repository.findAll();
    }

    @Override
    public PatientContact update(UUID id, PatientContactCommand command) {
        PatientContact existing = getById(id);
        PatientContact updated = new PatientContact(
                existing.id(),
                command.contactId(),
                command.patientId(),
                command.isPrimaryContact(),
                command.isEmergencyContact(),
                command.relationshipTypeId());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new PatientContactNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
