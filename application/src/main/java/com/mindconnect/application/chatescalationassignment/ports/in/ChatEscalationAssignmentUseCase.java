package com.mindconnect.application.chatescalationassignment.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatescalationassignment.dtos.ChatEscalationAssignmentCommand;
import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_escalation_assignments.
 */
public interface ChatEscalationAssignmentUseCase {

    ChatEscalationAssignment create(ChatEscalationAssignmentCommand command);

    ChatEscalationAssignment getById(UUID id);

    List<ChatEscalationAssignment> getAll();

    ChatEscalationAssignment update(UUID id, ChatEscalationAssignmentCommand command);

    void delete(UUID id);
}
