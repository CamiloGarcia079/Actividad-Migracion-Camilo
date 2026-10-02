package com.mindconnect.application.messagetype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.messagetype.dtos.MessageTypeCommand;
import com.mindconnect.application.messagetype.exceptions.MessageTypeNotFoundException;
import com.mindconnect.application.messagetype.ports.in.MessageTypeUseCase;
import com.mindconnect.domain.messagetype.model.aggregates.MessageType;
import com.mindconnect.domain.messagetype.repositories.MessageTypeRepository;

/**
 * Implementación de los casos de uso de message_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class MessageTypeService implements MessageTypeUseCase {

    private final MessageTypeRepository repository;

    public MessageTypeService(MessageTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public MessageType create(MessageTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        MessageType messageType = new MessageType(
                UUID.randomUUID(),
                command.nameType(),
                now,
                now);
        return repository.save(messageType);
    }

    @Override
    public MessageType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new MessageTypeNotFoundException(id));
    }

    @Override
    public List<MessageType> getAll() {
        return repository.findAll();
    }

    @Override
    public MessageType update(UUID id, MessageTypeCommand command) {
        MessageType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        MessageType updated = new MessageType(
                existing.id(),
                command.nameType(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new MessageTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
