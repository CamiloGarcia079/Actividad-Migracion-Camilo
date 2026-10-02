package com.mindconnect.application.treatmentstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentstatus.dtos.TreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.exceptions.TreatmentStatusNotFoundException;
import com.mindconnect.application.treatmentstatus.ports.in.TreatmentStatusUseCase;
import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.repositories.TreatmentStatusRepository;

/**
 * Implementación de los casos de uso de treatment_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class TreatmentStatusService implements TreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;

    public TreatmentStatusService(TreatmentStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public TreatmentStatus create(TreatmentStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        TreatmentStatus treatmentStatus = new TreatmentStatus(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(treatmentStatus);
    }

    @Override
    public TreatmentStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TreatmentStatusNotFoundException(id));
    }

    @Override
    public List<TreatmentStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public TreatmentStatus update(UUID id, TreatmentStatusCommand command) {
        TreatmentStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        TreatmentStatus updated = new TreatmentStatus(
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
            throw new TreatmentStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
