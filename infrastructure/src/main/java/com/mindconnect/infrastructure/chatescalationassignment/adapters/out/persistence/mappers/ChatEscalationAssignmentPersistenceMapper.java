package com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_escalation_assignments.
 */
@Component
public class ChatEscalationAssignmentPersistenceMapper {

    public ChatEscalationAssignmentJpaEntity toEntity(ChatEscalationAssignment chatEscalationAssignment) {
        ChatEscalationAssignmentJpaEntity entity = new ChatEscalationAssignmentJpaEntity();
        entity.setId(chatEscalationAssignment.id());
        entity.setEscalationId(chatEscalationAssignment.escalationId());
        entity.setProfessionalId(chatEscalationAssignment.professionalId());
        entity.setAssignedAt(chatEscalationAssignment.assignedAt());
        return entity;
    }

    public ChatEscalationAssignment toDomain(ChatEscalationAssignmentJpaEntity entity) {
        return new ChatEscalationAssignment(
                entity.getId(),
                entity.getEscalationId(),
                entity.getProfessionalId(),
                entity.getAssignedAt());
    }
}
