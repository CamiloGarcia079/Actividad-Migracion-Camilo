package com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_ai_run_metrics.
 */
@Component
public class ChatAiRunMetricPersistenceMapper {

    public ChatAiRunMetricJpaEntity toEntity(ChatAiRunMetric chatAiRunMetric) {
        ChatAiRunMetricJpaEntity entity = new ChatAiRunMetricJpaEntity();
        entity.setId(chatAiRunMetric.id());
        entity.setAiRunId(chatAiRunMetric.aiRunId());
        entity.setPromptTokens(chatAiRunMetric.promptTokens());
        entity.setCompletionTokens(chatAiRunMetric.completionTokens());
        entity.setTotalTokens(chatAiRunMetric.totalTokens());
        entity.setCost(chatAiRunMetric.cost());
        entity.setCreatedAt(chatAiRunMetric.createdAt());
        return entity;
    }

    public ChatAiRunMetric toDomain(ChatAiRunMetricJpaEntity entity) {
        return new ChatAiRunMetric(
                entity.getId(),
                entity.getAiRunId(),
                entity.getPromptTokens(),
                entity.getCompletionTokens(),
                entity.getTotalTokens(),
                entity.getCost(),
                entity.getCreatedAt());
    }
}
