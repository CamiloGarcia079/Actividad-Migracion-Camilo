package com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de chat_conversation_ai_settings.
 */
@Component
public class ChatConversationAiSettingPersistenceMapper {

    public ChatConversationAiSettingJpaEntity toEntity(ChatConversationAiSetting chatConversationAiSetting) {
        ChatConversationAiSettingJpaEntity entity = new ChatConversationAiSettingJpaEntity();
        entity.setId(chatConversationAiSetting.id());
        entity.setConversationId(chatConversationAiSetting.conversationId());
        entity.setAiEnabled(chatConversationAiSetting.aiEnabled());
        entity.setDefaultModelId(chatConversationAiSetting.defaultModelId());
        entity.setCreatedAt(chatConversationAiSetting.createdAt());
        entity.setUpdatedAt(chatConversationAiSetting.updatedAt());
        return entity;
    }

    public ChatConversationAiSetting toDomain(ChatConversationAiSettingJpaEntity entity) {
        return new ChatConversationAiSetting(
                entity.getId(),
                entity.getConversationId(),
                entity.getAiEnabled(),
                entity.getDefaultModelId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
