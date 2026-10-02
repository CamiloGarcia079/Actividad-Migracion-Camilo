package com.mindconnect.application.professionalstudy.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professionalstudy.dtos.ProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.exceptions.ProfessionalStudyNotFoundException;
import com.mindconnect.application.professionalstudy.ports.in.ProfessionalStudyUseCase;
import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.repositories.ProfessionalStudyRepository;

/**
 * Implementación de los casos de uso de professional_studies.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ProfessionalStudyService implements ProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;

    public ProfessionalStudyService(ProfessionalStudyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalStudy create(ProfessionalStudyCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ProfessionalStudy professionalStudy = new ProfessionalStudy(
                UUID.randomUUID(),
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.isValid(),
                command.resolutionNumber(),
                command.countryId(),
                now,
                now);
        return repository.save(professionalStudy);
    }

    @Override
    public ProfessionalStudy getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfessionalStudyNotFoundException(id));
    }

    @Override
    public List<ProfessionalStudy> getAll() {
        return repository.findAll();
    }

    @Override
    public ProfessionalStudy update(UUID id, ProfessionalStudyCommand command) {
        ProfessionalStudy existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ProfessionalStudy updated = new ProfessionalStudy(
                existing.id(),
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.isValid(),
                command.resolutionNumber(),
                command.countryId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ProfessionalStudyNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
