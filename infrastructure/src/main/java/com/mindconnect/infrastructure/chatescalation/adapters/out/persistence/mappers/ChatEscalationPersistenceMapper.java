package com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;
import com.mindconnect.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_escalations.
 */
@Component
public class ChatEscalationPersistenceMapper {

    public ChatEscalationJpaEntity toEntity(ChatEscalation chatEscalation) {
        ChatEscalationJpaEntity entity = new ChatEscalationJpaEntity();
        entity.setId(chatEscalation.id());
        entity.setConversationId(chatEscalation.conversationId());
        entity.setStatusId(chatEscalation.statusId());
        entity.setFromAi(chatEscalation.fromAi());
        entity.setReason(chatEscalation.reason());
        entity.setCreatedAt(chatEscalation.createdAt());
        return entity;
    }

    public ChatEscalation toDomain(ChatEscalationJpaEntity entity) {
        return new ChatEscalation(
                entity.getId(),
                entity.getConversationId(),
                entity.getStatusId(),
                entity.getFromAi(),
                entity.getReason(),
                entity.getCreatedAt());
    }
}
