package com.mindconnect.application.sendertype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.sendertype.dtos.SenderTypeCommand;
import com.mindconnect.application.sendertype.exceptions.SenderTypeNotFoundException;
import com.mindconnect.application.sendertype.ports.in.SenderTypeUseCase;
import com.mindconnect.domain.sendertype.model.aggregates.SenderType;
import com.mindconnect.domain.sendertype.repositories.SenderTypeRepository;

/**
 * Implementación de los casos de uso de sender_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class SenderTypeService implements SenderTypeUseCase {

    private final SenderTypeRepository repository;

    public SenderTypeService(SenderTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public SenderType create(SenderTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        SenderType senderType = new SenderType(
                UUID.randomUUID(),
                command.nameType(),
                now,
                now);
        return repository.save(senderType);
    }

    @Override
    public SenderType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new SenderTypeNotFoundException(id));
    }

    @Override
    public List<SenderType> getAll() {
        return repository.findAll();
    }

    @Override
    public SenderType update(UUID id, SenderTypeCommand command) {
        SenderType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        SenderType updated = new SenderType(
                existing.id(),
                command.nameType(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new SenderTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
