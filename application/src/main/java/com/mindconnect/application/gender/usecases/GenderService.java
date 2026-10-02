package com.mindconnect.application.gender.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.gender.dtos.GenderCommand;
import com.mindconnect.application.gender.exceptions.GenderNotFoundException;
import com.mindconnect.application.gender.ports.in.GenderUseCase;
import com.mindconnect.domain.gender.model.aggregates.Gender;
import com.mindconnect.domain.gender.repositories.GenderRepository;

/**
 * Implementación de los casos de uso de genders.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class GenderService implements GenderUseCase {

    private final GenderRepository repository;

    public GenderService(GenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Gender create(GenderCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Gender gender = new Gender(
                UUID.randomUUID(),
                command.description(),
                now,
                now);
        return repository.save(gender);
    }

    @Override
    public Gender getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new GenderNotFoundException(id));
    }

    @Override
    public List<Gender> getAll() {
        return repository.findAll();
    }

    @Override
    public Gender update(UUID id, GenderCommand command) {
        Gender existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Gender updated = new Gender(
                existing.id(),
                command.description(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new GenderNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
