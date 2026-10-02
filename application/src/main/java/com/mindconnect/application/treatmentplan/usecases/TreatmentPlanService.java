package com.mindconnect.application.treatmentplan.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.treatmentplan.dtos.TreatmentPlanCommand;
import com.mindconnect.application.treatmentplan.exceptions.TreatmentPlanNotFoundException;
import com.mindconnect.application.treatmentplan.ports.in.TreatmentPlanUseCase;
import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.repositories.TreatmentPlanRepository;

/**
 * Implementación de los casos de uso de treatment_plans.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class TreatmentPlanService implements TreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;

    public TreatmentPlanService(TreatmentPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public TreatmentPlan create(TreatmentPlanCommand command) {
        LocalDateTime now = LocalDateTime.now();
        TreatmentPlan treatmentPlan = new TreatmentPlan(
                UUID.randomUUID(),
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.treatmentStatusId(),
                now,
                now);
        return repository.save(treatmentPlan);
    }

    @Override
    public TreatmentPlan getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TreatmentPlanNotFoundException(id));
    }

    @Override
    public List<TreatmentPlan> getAll() {
        return repository.findAll();
    }

    @Override
    public TreatmentPlan update(UUID id, TreatmentPlanCommand command) {
        TreatmentPlan existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        TreatmentPlan updated = new TreatmentPlan(
                existing.id(),
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.treatmentStatusId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new TreatmentPlanNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
