package com.mindconnect.application.encountermodality.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.encountermodality.dtos.EncounterModalityCommand;
import com.mindconnect.application.encountermodality.exceptions.EncounterModalityNotFoundException;
import com.mindconnect.application.encountermodality.ports.in.EncounterModalityUseCase;
import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;
import com.mindconnect.domain.encountermodality.repositories.EncounterModalityRepository;

/**
 * Implementación de los casos de uso de encounter_modalities.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EncounterModalityService implements EncounterModalityUseCase {

    private final EncounterModalityRepository repository;

    public EncounterModalityService(EncounterModalityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EncounterModality create(EncounterModalityCommand command) {
        LocalDateTime now = LocalDateTime.now();
        EncounterModality encounterModality = new EncounterModality(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(encounterModality);
    }

    @Override
    public EncounterModality getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EncounterModalityNotFoundException(id));
    }

    @Override
    public List<EncounterModality> getAll() {
        return repository.findAll();
    }

    @Override
    public EncounterModality update(UUID id, EncounterModalityCommand command) {
        EncounterModality existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        EncounterModality updated = new EncounterModality(
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
            throw new EncounterModalityNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
