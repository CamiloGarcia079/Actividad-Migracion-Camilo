package com.mindconnect.application.conversationstatus.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.conversationstatus.dtos.ConversationStatusCommand;
import com.mindconnect.application.conversationstatus.exceptions.ConversationStatusNotFoundException;
import com.mindconnect.application.conversationstatus.ports.in.ConversationStatusUseCase;
import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;
import com.mindconnect.domain.conversationstatus.repositories.ConversationStatusRepository;

/**
 * Implementación de los casos de uso de conversations_statuses.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ConversationStatusService implements ConversationStatusUseCase {

    private final ConversationStatusRepository repository;

    public ConversationStatusService(ConversationStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConversationStatus create(ConversationStatusCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ConversationStatus conversationStatus = new ConversationStatus(
                UUID.randomUUID(),
                command.nameStatus(),
                now,
                now);
        return repository.save(conversationStatus);
    }

    @Override
    public ConversationStatus getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ConversationStatusNotFoundException(id));
    }

    @Override
    public List<ConversationStatus> getAll() {
        return repository.findAll();
    }

    @Override
    public ConversationStatus update(UUID id, ConversationStatusCommand command) {
        ConversationStatus existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ConversationStatus updated = new ConversationStatus(
                existing.id(),
                command.nameStatus(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ConversationStatusNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
