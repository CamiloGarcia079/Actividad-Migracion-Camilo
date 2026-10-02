package com.mindconnect.application.chatairunerror.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairunerror.dtos.ChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.exceptions.ChatAiRunErrorNotFoundException;
import com.mindconnect.application.chatairunerror.ports.in.ChatAiRunErrorUseCase;
import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.repositories.ChatAiRunErrorRepository;

/**
 * Implementación de los casos de uso de chat_ai_run_errors.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatAiRunErrorService implements ChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public ChatAiRunErrorService(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatAiRunError create(ChatAiRunErrorCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunError chatAiRunError = new ChatAiRunError(
                UUID.randomUUID(),
                command.aiRunId(),
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId(),
                now);
        return repository.save(chatAiRunError);
    }

    @Override
    public ChatAiRunError getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundException(id));
    }

    @Override
    public List<ChatAiRunError> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatAiRunError update(UUID id, ChatAiRunErrorCommand command) {
        ChatAiRunError existing = getById(id);
        ChatAiRunError updated = new ChatAiRunError(
                existing.id(),
                command.aiRunId(),
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId(),
                existing.createdAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatAiRunErrorNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
