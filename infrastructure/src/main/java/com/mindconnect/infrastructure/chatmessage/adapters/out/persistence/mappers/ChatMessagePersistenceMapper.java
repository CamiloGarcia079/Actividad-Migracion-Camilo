package com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_messages.
 */
@Component
public class ChatMessagePersistenceMapper {

    public ChatMessageJpaEntity toEntity(ChatMessage chatMessage) {
        ChatMessageJpaEntity entity = new ChatMessageJpaEntity();
        entity.setId(chatMessage.id());
        entity.setConversationId(chatMessage.conversationId());
        entity.setMessageTypeId(chatMessage.messageTypeId());
        entity.setParticipantId(chatMessage.participantId());
        entity.setContent(chatMessage.content());
        entity.setMetadata(chatMessage.metadata());
        entity.setCreatedAt(chatMessage.createdAt());
        return entity;
    }

    public ChatMessage toDomain(ChatMessageJpaEntity entity) {
        return new ChatMessage(
                entity.getId(),
                entity.getConversationId(),
                entity.getMessageTypeId(),
                entity.getParticipantId(),
                entity.getContent(),
                entity.getMetadata(),
                entity.getCreatedAt());
    }
}
