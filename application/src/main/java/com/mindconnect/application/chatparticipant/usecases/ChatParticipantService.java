package com.mindconnect.application.chatparticipant.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatparticipant.dtos.ChatParticipantCommand;
import com.mindconnect.application.chatparticipant.exceptions.ChatParticipantNotFoundException;
import com.mindconnect.application.chatparticipant.ports.in.ChatParticipantUseCase;
import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;
import com.mindconnect.domain.chatparticipant.repositories.ChatParticipantRepository;

/**
 * Implementación de los casos de uso de chat_participants.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatParticipantService implements ChatParticipantUseCase {

    private final ChatParticipantRepository repository;

    public ChatParticipantService(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatParticipant create(ChatParticipantCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatParticipant chatParticipant = new ChatParticipant(
                UUID.randomUUID(),
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId(),
                now,
                now);
        return repository.save(chatParticipant);
    }

    @Override
    public ChatParticipant getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatParticipantNotFoundException(id));
    }

    @Override
    public List<ChatParticipant> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatParticipant update(UUID id, ChatParticipantCommand command) {
        ChatParticipant existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ChatParticipant updated = new ChatParticipant(
                existing.id(),
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatParticipantNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
