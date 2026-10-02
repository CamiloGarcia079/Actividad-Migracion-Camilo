package com.mindconnect.application.encounter.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encounter.dtos.EncounterCommand;
import com.mindconnect.application.encounter.exceptions.EncounterNotFoundException;
import com.mindconnect.application.encounter.ports.in.EncounterUseCase;
import com.mindconnect.domain.encounter.model.aggregates.Encounter;
import com.mindconnect.domain.encounter.repositories.EncounterRepository;

/**
 * Implementación de los casos de uso de encounters.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EncounterService implements EncounterUseCase {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Encounter create(EncounterCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Encounter encounter = new Encounter(
                UUID.randomUUID(),
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                now,
                command.createdBy(),
                now,
                command.updatedBy());
        return repository.save(encounter);
    }

    @Override
    public Encounter getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundException(id));
    }

    @Override
    public List<Encounter> getAll() {
        return repository.findAll();
    }

    @Override
    public Encounter update(UUID id, EncounterCommand command) {
        Encounter existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Encounter updated = new Encounter(
                existing.id(),
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                existing.createdAt(),
                command.createdBy(),
                now,
                command.updatedBy());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EncounterNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
