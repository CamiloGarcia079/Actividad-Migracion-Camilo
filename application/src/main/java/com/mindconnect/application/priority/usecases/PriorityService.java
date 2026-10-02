package com.mindconnect.application.priority.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.priority.dtos.PriorityCommand;
import com.mindconnect.application.priority.exceptions.PriorityNotFoundException;
import com.mindconnect.application.priority.ports.in.PriorityUseCase;
import com.mindconnect.domain.priority.model.aggregates.Priority;
import com.mindconnect.domain.priority.repositories.PriorityRepository;

/**
 * Implementación de los casos de uso de priorities.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class PriorityService implements PriorityUseCase {

    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Priority create(PriorityCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Priority priority = new Priority(
                UUID.randomUUID(),
                command.namePriority(),
                now,
                now);
        return repository.save(priority);
    }

    @Override
    public Priority getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id));
    }

    @Override
    public List<Priority> getAll() {
        return repository.findAll();
    }

    @Override
    public Priority update(UUID id, PriorityCommand command) {
        Priority existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Priority updated = new Priority(
                existing.id(),
                command.namePriority(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new PriorityNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
