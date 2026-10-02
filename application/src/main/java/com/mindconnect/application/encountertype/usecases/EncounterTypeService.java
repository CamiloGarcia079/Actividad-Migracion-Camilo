package com.mindconnect.application.encountertype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encountertype.dtos.EncounterTypeCommand;
import com.mindconnect.application.encountertype.exceptions.EncounterTypeNotFoundException;
import com.mindconnect.application.encountertype.ports.in.EncounterTypeUseCase;
import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;
import com.mindconnect.domain.encountertype.repositories.EncounterTypeRepository;

/**
 * Implementación de los casos de uso de encounter_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EncounterTypeService implements EncounterTypeUseCase {

    private final EncounterTypeRepository repository;

    public EncounterTypeService(EncounterTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EncounterType create(EncounterTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        EncounterType encounterType = new EncounterType(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(encounterType);
    }

    @Override
    public EncounterType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterTypeNotFoundException(id));
    }

    @Override
    public List<EncounterType> getAll() {
        return repository.findAll();
    }

    @Override
    public EncounterType update(UUID id, EncounterTypeCommand command) {
        EncounterType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        EncounterType updated = new EncounterType(
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
            throw new EncounterTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
