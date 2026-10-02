package com.mindconnect.application.professional.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.professional.dtos.ProfessionalCommand;
import com.mindconnect.application.professional.exceptions.ProfessionalNotFoundException;
import com.mindconnect.application.professional.ports.in.ProfessionalUseCase;
import com.mindconnect.domain.professional.model.aggregates.Professional;
import com.mindconnect.domain.professional.repositories.ProfessionalRepository;

/**
 * Implementación de los casos de uso de professionals.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ProfessionalService implements ProfessionalUseCase {

    private final ProfessionalRepository repository;

    public ProfessionalService(ProfessionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Professional create(ProfessionalCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Professional professional = new Professional(
                UUID.randomUUID(),
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalType(),
                command.licenseNumber(),
                command.active(),
                command.cityId(),
                now,
                now);
        return repository.save(professional);
    }

    @Override
    public Professional getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException(id));
    }

    @Override
    public List<Professional> getAll() {
        return repository.findAll();
    }

    @Override
    public Professional update(UUID id, ProfessionalCommand command) {
        Professional existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Professional updated = new Professional(
                existing.id(),
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalType(),
                command.licenseNumber(),
                command.active(),
                command.cityId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ProfessionalNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
