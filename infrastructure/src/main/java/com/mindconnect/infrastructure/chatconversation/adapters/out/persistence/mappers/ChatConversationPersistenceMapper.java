package com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_conversations.
 */
@Component
public class ChatConversationPersistenceMapper {

    public ChatConversationJpaEntity toEntity(ChatConversation chatConversation) {
        ChatConversationJpaEntity entity = new ChatConversationJpaEntity();
        entity.setId(chatConversation.id());
        entity.setConversationStatusId(chatConversation.conversationStatusId());
        entity.setPriorityId(chatConversation.priorityId());
        entity.setLastMessageAt(chatConversation.lastMessageAt());
        entity.setClosed(chatConversation.closed());
        entity.setClosedAt(chatConversation.closedAt());
        entity.setClosedBy(chatConversation.closedBy());
        entity.setCreatedAt(chatConversation.createdAt());
        entity.setUpdatedAt(chatConversation.updatedAt());
        return entity;
    }

    public ChatConversation toDomain(ChatConversationJpaEntity entity) {
        return new ChatConversation(
                entity.getId(),
                entity.getConversationStatusId(),
                entity.getPriorityId(),
                entity.getLastMessageAt(),
                entity.getClosed(),
                entity.getClosedAt(),
                entity.getClosedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
