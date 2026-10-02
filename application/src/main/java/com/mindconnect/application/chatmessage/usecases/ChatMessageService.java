package com.mindconnect.application.chatmessage.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatmessage.dtos.ChatMessageCommand;
import com.mindconnect.application.chatmessage.exceptions.ChatMessageNotFoundException;
import com.mindconnect.application.chatmessage.ports.in.ChatMessageUseCase;
import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;
import com.mindconnect.domain.chatmessage.repositories.ChatMessageRepository;

/**
 * Implementación de los casos de uso de chat_messages.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatMessageService implements ChatMessageUseCase {

    private final ChatMessageRepository repository;

    public ChatMessageService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatMessage create(ChatMessageCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatMessage chatMessage = new ChatMessage(
                UUID.randomUUID(),
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
                command.content(),
                command.metadata(),
                now);
        return repository.save(chatMessage);
    }

    @Override
    public ChatMessage getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatMessageNotFoundException(id));
    }

    @Override
    public List<ChatMessage> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatMessage update(UUID id, ChatMessageCommand command) {
        ChatMessage existing = getById(id);
        ChatMessage updated = new ChatMessage(
                existing.id(),
                command.conversationId(),
                command.messageTypeId(),
                command.participantId(),
                command.content(),
                command.metadata(),
                existing.createdAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatMessageNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
