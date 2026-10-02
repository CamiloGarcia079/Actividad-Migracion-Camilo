package com.mindconnect.application.professionaltype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professionaltype.dtos.ProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.exceptions.ProfessionalTypeNotFoundException;
import com.mindconnect.application.professionaltype.ports.in.ProfessionalTypeUseCase;
import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;
import com.mindconnect.domain.professionaltype.repositories.ProfessionalTypeRepository;

/**
 * Implementación de los casos de uso de professional_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ProfessionalTypeService implements ProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;

    public ProfessionalTypeService(ProfessionalTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfessionalType create(ProfessionalTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ProfessionalType professionalType = new ProfessionalType(
                UUID.randomUUID(),
                command.name(),
                now,
                now);
        return repository.save(professionalType);
    }

    @Override
    public ProfessionalType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfessionalTypeNotFoundException(id));
    }

    @Override
    public List<ProfessionalType> getAll() {
        return repository.findAll();
    }

    @Override
    public ProfessionalType update(UUID id, ProfessionalTypeCommand command) {
        ProfessionalType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ProfessionalType updated = new ProfessionalType(
                existing.id(),
                command.name(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ProfessionalTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
