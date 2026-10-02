package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_escalation_status_history.
 */
@Component
public class ChatEscalationStatusHistoryPersistenceMapper {

    public ChatEscalationStatusHistoryJpaEntity toEntity(ChatEscalationStatusHistory chatEscalationStatusHistory) {
        ChatEscalationStatusHistoryJpaEntity entity = new ChatEscalationStatusHistoryJpaEntity();
        entity.setId(chatEscalationStatusHistory.id());
        entity.setEscalationId(chatEscalationStatusHistory.escalationId());
        entity.setEscalationStatusId(chatEscalationStatusHistory.escalationStatusId());
        entity.setCreatedAt(chatEscalationStatusHistory.createdAt());
        entity.setChangedAt(chatEscalationStatusHistory.changedAt());
        return entity;
    }

    public ChatEscalationStatusHistory toDomain(ChatEscalationStatusHistoryJpaEntity entity) {
        return new ChatEscalationStatusHistory(
                entity.getId(),
                entity.getEscalationId(),
                entity.getEscalationStatusId(),
                entity.getCreatedAt(),
                entity.getChangedAt());
    }
}
