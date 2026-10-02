package com.mindconnect.domain.chatconversationaisetting.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar chat_conversation_ai_settings.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface ChatConversationAiSettingRepository {

    ChatConversationAiSetting save(ChatConversationAiSetting chatConversationAiSetting);

    Optional<ChatConversationAiSetting> findById(UUID id);

    List<ChatConversationAiSetting> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
