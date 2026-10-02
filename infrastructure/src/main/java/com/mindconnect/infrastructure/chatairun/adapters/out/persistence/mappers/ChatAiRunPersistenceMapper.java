package com.mindconnect.infrastructure.chatairun.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;
import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_ai_runs.
 */
@Component
public class ChatAiRunPersistenceMapper {

    public ChatAiRunJpaEntity toEntity(ChatAiRun chatAiRun) {
        ChatAiRunJpaEntity entity = new ChatAiRunJpaEntity();
        entity.setId(chatAiRun.id());
        entity.setConversationId(chatAiRun.conversationId());
        entity.setMessageId(chatAiRun.messageId());
        entity.setModelId(chatAiRun.modelId());
        entity.setAiRunStatusId(chatAiRun.aiRunStatusId());
        entity.setCreatedAt(chatAiRun.createdAt());
        entity.setUpdatedAt(chatAiRun.updatedAt());
        return entity;
    }

    public ChatAiRun toDomain(ChatAiRunJpaEntity entity) {
        return new ChatAiRun(
                entity.getId(),
                entity.getConversationId(),
                entity.getMessageId(),
                entity.getModelId(),
                entity.getAiRunStatusId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
