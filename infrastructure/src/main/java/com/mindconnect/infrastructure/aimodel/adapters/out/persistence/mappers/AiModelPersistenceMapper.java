package com.mindconnect.infrastructure.aimodel.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.aimodel.model.aggregates.AiModel;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de ai_models.
 */
@Component
public class AiModelPersistenceMapper {

    public AiModelJpaEntity toEntity(AiModel aiModel) {
        AiModelJpaEntity entity = new AiModelJpaEntity();
        entity.setId(aiModel.id());
        entity.setProviderModelId(aiModel.providerModelId());
        entity.setNameModel(aiModel.nameModel());
        entity.setModelKey(aiModel.modelKey());
        entity.setInputTokenPrice(aiModel.inputTokenPrice());
        entity.setOutputTokenPrice(aiModel.outputTokenPrice());
        entity.setMaxTokens(aiModel.maxTokens());
        entity.setContextWindow(aiModel.contextWindow());
        entity.setIsActive(aiModel.isActive());
        entity.setCreatedAt(aiModel.createdAt());
        entity.setUpdatedAt(aiModel.updatedAt());
        return entity;
    }

    public AiModel toDomain(AiModelJpaEntity entity) {
        return new AiModel(
                entity.getId(),
                entity.getProviderModelId(),
                entity.getNameModel(),
                entity.getModelKey(),
                entity.getInputTokenPrice(),
                entity.getOutputTokenPrice(),
                entity.getMaxTokens(),
                entity.getContextWindow(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
