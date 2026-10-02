package com.mindconnect.application.escalationstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.escalationstatus.dtos.EscalationStatusCommand;
import com.mindconnect.application.escalationstatus.exceptions.EscalationStatusNotFoundException;
import com.mindconnect.application.escalationstatus.ports.in.EscalationStatusUseCase;
import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;
import com.mindconnect.domain.escalationstatus.repositories.EscalationStatusRepository;

/**
 * Implementación de los casos de uso de escalations_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class EscalationStatusService implements EscalationStatusUseCase {

    private final EscalationStatusRepository repository;

    public EscalationStatusService(EscalationStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public EscalationStatus create(EscalationStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        EscalationStatus escalationStatus = new EscalationStatus(
                UUID.randomUUID(),
                command.nameStatus(),
                now,
                now);
        return repository.save(escalationStatus);
    }

    @Override
    public EscalationStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EscalationStatusNotFoundException(id));
    }

    @Override
    public List<EscalationStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public EscalationStatus update(UUID id, EscalationStatusCommand command) {
        EscalationStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        EscalationStatus updated = new EscalationStatus(
                existing.id(),
                command.nameStatus(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EscalationStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
