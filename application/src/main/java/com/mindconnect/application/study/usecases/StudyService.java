package com.mindconnect.application.study.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.study.dtos.StudyCommand;
import com.mindconnect.application.study.exceptions.StudyNotFoundException;
import com.mindconnect.application.study.ports.in.StudyUseCase;
import com.mindconnect.domain.study.model.aggregates.Study;
import com.mindconnect.domain.study.repositories.StudyRepository;

/**
 * Implementación de los casos de uso de studies.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class StudyService implements StudyUseCase {

    private final StudyRepository repository;

    public StudyService(StudyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Study create(StudyCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Study study = new Study(
                UUID.randomUUID(),
                command.name(),
                now,
                now);
        return repository.save(study);
    }

    @Override
    public Study getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudyNotFoundException(id));
    }

    @Override
    public List<Study> getAll() {
        return repository.findAll();
    }

    @Override
    public Study update(UUID id, StudyCommand command) {
        Study existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Study updated = new Study(
                existing.id(),
                command.name(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new StudyNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
