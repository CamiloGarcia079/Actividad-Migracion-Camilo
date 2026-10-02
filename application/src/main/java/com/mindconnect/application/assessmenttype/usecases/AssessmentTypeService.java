package com.mindconnect.application.assessmenttype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.assessmenttype.dtos.AssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.exceptions.AssessmentTypeNotFoundException;
import com.mindconnect.application.assessmenttype.ports.in.AssessmentTypeUseCase;
import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;
import com.mindconnect.domain.assessmenttype.repositories.AssessmentTypeRepository;

/**
 * Implementación de los casos de uso de assessment_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class AssessmentTypeService implements AssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;

    public AssessmentTypeService(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssessmentType create(AssessmentTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        AssessmentType assessmentType = new AssessmentType(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                command.description(),
                now,
                now);
        return repository.save(assessmentType);
    }

    @Override
    public AssessmentType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AssessmentTypeNotFoundException(id));
    }

    @Override
    public List<AssessmentType> getAll() {
        return repository.findAll();
    }

    @Override
    public AssessmentType update(UUID id, AssessmentTypeCommand command) {
        AssessmentType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        AssessmentType updated = new AssessmentType(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                command.description(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new AssessmentTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
