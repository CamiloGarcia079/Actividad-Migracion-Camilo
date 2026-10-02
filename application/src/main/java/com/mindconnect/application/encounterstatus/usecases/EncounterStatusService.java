package com.mindconnect.application.encounterstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encounterstatus.dtos.EncounterStatusCommand;
import com.mindconnect.application.encounterstatus.exceptions.EncounterStatusNotFoundException;
import com.mindconnect.application.encounterstatus.ports.in.EncounterStatusUseCase;
import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;
import com.mindconnect.domain.encounterstatus.repositories.EncounterStatusRepository;

/**
 * Implementación de los casos de uso de encounter_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EncounterStatusService implements EncounterStatusUseCase {

    private final EncounterStatusRepository repository;

    public EncounterStatusService(EncounterStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public EncounterStatus create(EncounterStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        EncounterStatus encounterStatus = new EncounterStatus(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(encounterStatus);
    }

    @Override
    public EncounterStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterStatusNotFoundException(id));
    }

    @Override
    public List<EncounterStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public EncounterStatus update(UUID id, EncounterStatusCommand command) {
        EncounterStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        EncounterStatus updated = new EncounterStatus(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EncounterStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
