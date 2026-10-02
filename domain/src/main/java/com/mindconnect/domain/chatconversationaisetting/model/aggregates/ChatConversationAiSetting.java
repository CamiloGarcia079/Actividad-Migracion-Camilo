package com.mindconnect.domain.chatconversationaisetting.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla chat_conversation_ai_settings.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record ChatConversationAiSetting(
        UUID id,
        UUID conversationId,
        Boolean aiEnabled,
        UUID defaultModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public ChatConversationAiSetting {
        DomainValidations.required(id, "id");
        DomainValidations.required(conversationId, "conversationId");
        DomainValidations.required(aiEnabled, "aiEnabled");
        DomainValidations.required(defaultModelId, "defaultModelId");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
