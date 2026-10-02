package com.mindconnect.application.chatescalationassignment.usecases;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalationassignment.dtos.ChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.exceptions.ChatEscalationAssignmentNotFoundException;
import com.mindconnect.application.chatescalationassignment.ports.in.ChatEscalationAssignmentUseCase;
import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.repositories.ChatEscalationAssignmentRepository;

/**
 * Implementación de los casos de uso de chat_escalation_assignments.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatEscalationAssignmentService implements ChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public ChatEscalationAssignmentService(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatEscalationAssignment create(ChatEscalationAssignmentCommand command) {
        ChatEscalationAssignment chatEscalationAssignment = new ChatEscalationAssignment(
                UUID.randomUUID(),
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());
        return repository.save(chatEscalationAssignment);
    }

    @Override
    public ChatEscalationAssignment getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundException(id));
    }

    @Override
    public List<ChatEscalationAssignment> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatEscalationAssignment update(UUID id, ChatEscalationAssignmentCommand command) {
        ChatEscalationAssignment existing = getById(id);
        ChatEscalationAssignment updated = new ChatEscalationAssignment(
                existing.id(),
                command.escalationId(),
                command.professionalId(),
                command.assignedAt());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatEscalationAssignmentNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
