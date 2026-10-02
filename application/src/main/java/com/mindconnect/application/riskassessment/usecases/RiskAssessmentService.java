package com.mindconnect.application.riskassessment.usecases;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.riskassessment.dtos.RiskAssessmentCommand;
import com.mindconnect.application.riskassessment.exceptions.RiskAssessmentNotFoundException;
import com.mindconnect.application.riskassessment.ports.in.RiskAssessmentUseCase;
import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;
import com.mindconnect.domain.riskassessment.repositories.RiskAssessmentRepository;

/**
 * Implementación de los casos de uso de risk_assessments.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class RiskAssessmentService implements RiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentService(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskAssessment create(RiskAssessmentCommand command) {
        RiskAssessment riskAssessment = new RiskAssessment(
                UUID.randomUUID(),
                command.encounterId(),
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations(),
                command.assessedAt(),
                command.assessedBy());
        return repository.save(riskAssessment);
    }

    @Override
    public RiskAssessment getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RiskAssessmentNotFoundException(id));
    }

    @Override
    public List<RiskAssessment> getAll() {
        return repository.findAll();
    }

    @Override
    public RiskAssessment update(UUID id, RiskAssessmentCommand command) {
        RiskAssessment existing = getById(id);
        RiskAssessment updated = new RiskAssessment(
                existing.id(),
                command.encounterId(),
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations(),
                command.assessedAt(),
                command.assessedBy());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RiskAssessmentNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
