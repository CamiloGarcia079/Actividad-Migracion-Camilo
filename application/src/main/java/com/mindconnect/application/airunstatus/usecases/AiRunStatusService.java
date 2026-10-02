package com.mindconnect.application.airunstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.airunstatus.dtos.AiRunStatusCommand;
import com.mindconnect.application.airunstatus.exceptions.AiRunStatusNotFoundException;
import com.mindconnect.application.airunstatus.ports.in.AiRunStatusUseCase;
import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;
import com.mindconnect.domain.airunstatus.repositories.AiRunStatusRepository;

/**
 * Implementación de los casos de uso de ai_runs_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class AiRunStatusService implements AiRunStatusUseCase {

    private final AiRunStatusRepository repository;

    public AiRunStatusService(AiRunStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public AiRunStatus create(AiRunStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        AiRunStatus aiRunStatus = new AiRunStatus(
                UUID.randomUUID(),
                command.nameStatus(),
                now,
                now);
        return repository.save(aiRunStatus);
    }

    @Override
    public AiRunStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AiRunStatusNotFoundException(id));
    }

    @Override
    public List<AiRunStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public AiRunStatus update(UUID id, AiRunStatusCommand command) {
        AiRunStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        AiRunStatus updated = new AiRunStatus(
                existing.id(),
                command.nameStatus(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new AiRunStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
