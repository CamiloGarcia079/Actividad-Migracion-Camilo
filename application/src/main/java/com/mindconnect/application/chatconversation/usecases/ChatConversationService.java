package com.mindconnect.application.chatconversation.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatconversation.dtos.ChatConversationCommand;
import com.mindconnect.application.chatconversation.exceptions.ChatConversationNotFoundException;
import com.mindconnect.application.chatconversation.ports.in.ChatConversationUseCase;
import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;
import com.mindconnect.domain.chatconversation.repositories.ChatConversationRepository;

/**
 * Implementación de los casos de uso de chat_conversations.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatConversationService implements ChatConversationUseCase {

    private final ChatConversationRepository repository;

    public ChatConversationService(ChatConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatConversation create(ChatConversationCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatConversation chatConversation = new ChatConversation(
                UUID.randomUUID(),
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy(),
                now,
                now);
        return repository.save(chatConversation);
    }

    @Override
    public ChatConversation getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatConversationNotFoundException(id));
    }

    @Override
    public List<ChatConversation> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatConversation update(UUID id, ChatConversationCommand command) {
        ChatConversation existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ChatConversation updated = new ChatConversation(
                existing.id(),
                command.conversationStatusId(),
                command.priorityId(),
                command.lastMessageAt(),
                command.closed(),
                command.closedAt(),
                command.closedBy(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatConversationNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
