package com.mindconnect.domain.chatescalationassignment.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_escalation_assignments.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatEscalationAssignmentRepository {

    ChatEscalationAssignment save(ChatEscalationAssignment chatEscalationAssignment);

    Optional<ChatEscalationAssignment> findById(UUID id);

    List<ChatEscalationAssignment> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
