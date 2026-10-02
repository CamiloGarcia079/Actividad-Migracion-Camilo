package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_ai_run_errors.
 */
@Component
public class ChatAiRunErrorPersistenceMapper {

    public ChatAiRunErrorJpaEntity toEntity(ChatAiRunError chatAiRunError) {
        ChatAiRunErrorJpaEntity entity = new ChatAiRunErrorJpaEntity();
        entity.setId(chatAiRunError.id());
        entity.setAiRunId(chatAiRunError.aiRunId());
        entity.setErrorMessage(chatAiRunError.errorMessage());
        entity.setErrorCode(chatAiRunError.errorCode());
        entity.setProviderErrorId(chatAiRunError.providerErrorId());
        entity.setCreatedAt(chatAiRunError.createdAt());
        return entity;
    }

    public ChatAiRunError toDomain(ChatAiRunErrorJpaEntity entity) {
        return new ChatAiRunError(
                entity.getId(),
                entity.getAiRunId(),
                entity.getErrorMessage(),
                entity.getErrorCode(),
                entity.getProviderErrorId(),
                entity.getCreatedAt());
    }
}
