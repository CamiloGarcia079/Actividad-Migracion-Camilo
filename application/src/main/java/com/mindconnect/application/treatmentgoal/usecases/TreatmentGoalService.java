package com.mindconnect.application.treatmentgoal.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentgoal.dtos.TreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.exceptions.TreatmentGoalNotFoundException;
import com.mindconnect.application.treatmentgoal.ports.in.TreatmentGoalUseCase;
import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.repositories.TreatmentGoalRepository;

/**
 * Implementación de los casos de uso de treatment_goals.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class TreatmentGoalService implements TreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;

    public TreatmentGoalService(TreatmentGoalRepository repository) {
        this.repository = repository;
    }

    @Override
    public TreatmentGoal create(TreatmentGoalCommand command) {
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoal treatmentGoal = new TreatmentGoal(
                UUID.randomUUID(),
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.treatmentGoalId(),
                now,
                now);
        return repository.save(treatmentGoal);
    }

    @Override
    public TreatmentGoal getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalNotFoundException(id));
    }

    @Override
    public List<TreatmentGoal> getAll() {
        return repository.findAll();
    }

    @Override
    public TreatmentGoal update(UUID id, TreatmentGoalCommand command) {
        TreatmentGoal existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        TreatmentGoal updated = new TreatmentGoal(
                existing.id(),
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.treatmentGoalId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new TreatmentGoalNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
