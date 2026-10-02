package com.mindconnect.application.stateregion.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.stateregion.dtos.StateRegionCommand;
import com.mindconnect.application.stateregion.exceptions.StateRegionNotFoundException;
import com.mindconnect.application.stateregion.ports.in.StateRegionUseCase;
import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;
import com.mindconnect.domain.stateregion.repositories.StateRegionRepository;

/**
 * Implementación de los casos de uso de state_regions.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class StateRegionService implements StateRegionUseCase {

    private final StateRegionRepository repository;

    public StateRegionService(StateRegionRepository repository) {
        this.repository = repository;
    }

    @Override
    public StateRegion create(StateRegionCommand command) {
        LocalDateTime now = LocalDateTime.now();
        StateRegion stateRegion = new StateRegion(
                UUID.randomUUID(),
                command.nameRegion(),
                command.codeRegion(),
                command.description(),
                command.isActive(),
                command.countryId(),
                now,
                now);
        return repository.save(stateRegion);
    }

    @Override
    public StateRegion getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new StateRegionNotFoundException(id));
    }

    @Override
    public List<StateRegion> getAll() {
        return repository.findAll();
    }

    @Override
    public StateRegion update(UUID id, StateRegionCommand command) {
        StateRegion existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        StateRegion updated = new StateRegion(
                existing.id(),
                command.nameRegion(),
                command.codeRegion(),
                command.description(),
                command.isActive(),
                command.countryId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new StateRegionNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
