package com.mindconnect.application.chatairun.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatairun.dtos.ChatAiRunCommand;
import com.mindconnect.application.chatairun.exceptions.ChatAiRunNotFoundException;
import com.mindconnect.application.chatairun.ports.in.ChatAiRunUseCase;
import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;
import com.mindconnect.domain.chatairun.repositories.ChatAiRunRepository;

/**
 * Implementación de los casos de uso de chat_ai_runs.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatAiRunService implements ChatAiRunUseCase {

    private final ChatAiRunRepository repository;

    public ChatAiRunService(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatAiRun create(ChatAiRunCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatAiRun chatAiRun = new ChatAiRun(
                UUID.randomUUID(),
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId(),
                now,
                now);
        return repository.save(chatAiRun);
    }

    @Override
    public ChatAiRun getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatAiRunNotFoundException(id));
    }

    @Override
    public List<ChatAiRun> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatAiRun update(UUID id, ChatAiRunCommand command) {
        ChatAiRun existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ChatAiRun updated = new ChatAiRun(
                existing.id(),
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatAiRunNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
