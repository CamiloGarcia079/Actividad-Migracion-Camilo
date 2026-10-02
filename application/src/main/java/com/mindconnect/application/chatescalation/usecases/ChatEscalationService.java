package com.mindconnect.application.chatescalation.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalation.dtos.ChatEscalationCommand;
import com.mindconnect.application.chatescalation.exceptions.ChatEscalationNotFoundException;
import com.mindconnect.application.chatescalation.ports.in.ChatEscalationUseCase;
import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;
import com.mindconnect.domain.chatescalation.repositories.ChatEscalationRepository;

/**
 * Implementación de los casos de uso de chat_escalations.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatEscalationService implements ChatEscalationUseCase {

    private final ChatEscalationRepository repository;

    public ChatEscalationService(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatEscalation create(ChatEscalationCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatEscalation chatEscalation = new ChatEscalation(
                UUID.randomUUID(),
                command.conversationId(),
                command.statusId(),
                command.fromAi(),
                command.reason(),
                now);
        return repository.save(chatEscalation);
    }

    @Override
    public ChatEscalation getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatEscalationNotFoundException(id));
    }

    @Override
    public List<ChatEscalation> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatEscalation update(UUID id, ChatEscalationCommand command) {
        ChatEscalation existing = getById(id);
        ChatEscalation updated = new ChatEscalation(
                existing.id(),
                command.conversationId(),
                command.statusId(),
                command.fromAi(),
                command.reason(),
                existing.createdAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatEscalationNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
