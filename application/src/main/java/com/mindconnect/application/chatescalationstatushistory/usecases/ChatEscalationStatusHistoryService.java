package com.mindconnect.application.chatescalationstatushistory.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalationstatushistory.dtos.ChatEscalationStatusHistoryCommand;
import com.mindconnect.application.chatescalationstatushistory.exceptions.ChatEscalationStatusHistoryNotFoundException;
import com.mindconnect.application.chatescalationstatushistory.ports.in.ChatEscalationStatusHistoryUseCase;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.repositories.ChatEscalationStatusHistoryRepository;

/**
 * Implementación de los casos de uso de chat_escalation_status_history.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatEscalationStatusHistoryService implements ChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public ChatEscalationStatusHistoryService(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatEscalationStatusHistory create(ChatEscalationStatusHistoryCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatEscalationStatusHistory chatEscalationStatusHistory = new ChatEscalationStatusHistory(
                UUID.randomUUID(),
                command.escalationId(),
                command.escalationStatusId(),
                now,
                command.changedAt());
        return repository.save(chatEscalationStatusHistory);
    }

    @Override
    public ChatEscalationStatusHistory getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundException(id));
    }

    @Override
    public List<ChatEscalationStatusHistory> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatEscalationStatusHistory update(UUID id, ChatEscalationStatusHistoryCommand command) {
        ChatEscalationStatusHistory existing = getById(id);
        ChatEscalationStatusHistory updated = new ChatEscalationStatusHistory(
                existing.id(),
                command.escalationId(),
                command.escalationStatusId(),
                existing.createdAt(),
                command.changedAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatEscalationStatusHistoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
