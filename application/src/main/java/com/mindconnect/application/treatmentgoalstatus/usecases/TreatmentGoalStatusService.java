package com.mindconnect.application.treatmentgoalstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentgoalstatus.dtos.TreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.exceptions.TreatmentGoalStatusNotFoundException;
import com.mindconnect.application.treatmentgoalstatus.ports.in.TreatmentGoalStatusUseCase;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.repositories.TreatmentGoalStatusRepository;

/**
 * Implementación de los casos de uso de treatment_goal_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class TreatmentGoalStatusService implements TreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;

    public TreatmentGoalStatusService(TreatmentGoalStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public TreatmentGoalStatus create(TreatmentGoalStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoalStatus treatmentGoalStatus = new TreatmentGoalStatus(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(treatmentGoalStatus);
    }

    @Override
    public TreatmentGoalStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundException(id));
    }

    @Override
    public List<TreatmentGoalStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public TreatmentGoalStatus update(UUID id, TreatmentGoalStatusCommand command) {
        TreatmentGoalStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoalStatus updated = new TreatmentGoalStatus(
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
            throw new TreatmentGoalStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
