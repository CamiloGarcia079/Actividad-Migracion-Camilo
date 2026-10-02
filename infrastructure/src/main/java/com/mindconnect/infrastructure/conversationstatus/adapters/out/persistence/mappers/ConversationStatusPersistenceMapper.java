package com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;
import com.mindconnect.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de conversations_statuses.
 */
@Component
public class ConversationStatusPersistenceMapper {

    public ConversationStatusJpaEntity toEntity(ConversationStatus conversationStatus) {
        ConversationStatusJpaEntity entity = new ConversationStatusJpaEntity();
        entity.setId(conversationStatus.id());
        entity.setNameStatus(conversationStatus.nameStatus());
        entity.setCreatedAt(conversationStatus.createdAt());
        entity.setUpdatedAt(conversationStatus.updatedAt());
        return entity;
    }

    public ConversationStatus toDomain(ConversationStatusJpaEntity entity) {
        return new ConversationStatus(
                entity.getId(),
                entity.getNameStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
