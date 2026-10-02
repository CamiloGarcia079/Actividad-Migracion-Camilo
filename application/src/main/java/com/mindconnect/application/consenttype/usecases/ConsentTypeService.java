package com.mindconnect.application.consenttype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.consenttype.dtos.ConsentTypeCommand;
import com.mindconnect.application.consenttype.exceptions.ConsentTypeNotFoundException;
import com.mindconnect.application.consenttype.ports.in.ConsentTypeUseCase;
import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;
import com.mindconnect.domain.consenttype.repositories.ConsentTypeRepository;

/**
 * Implementación de los casos de uso de consent_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ConsentTypeService implements ConsentTypeUseCase {

    private final ConsentTypeRepository repository;

    public ConsentTypeService(ConsentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsentType create(ConsentTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ConsentType consentType = new ConsentType(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                command.description(),
                now,
                now);
        return repository.save(consentType);
    }

    @Override
    public ConsentType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConsentTypeNotFoundException(id));
    }

    @Override
    public List<ConsentType> getAll() {
        return repository.findAll();
    }

    @Override
    public ConsentType update(UUID id, ConsentTypeCommand command) {
        ConsentType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ConsentType updated = new ConsentType(
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
            throw new ConsentTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
