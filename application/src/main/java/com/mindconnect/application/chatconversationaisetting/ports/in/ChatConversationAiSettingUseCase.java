package com.mindconnect.application.chatconversationaisetting.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatconversationaisetting.dtos.ChatConversationAiSettingCommand;
import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre chat_conversation_ai_settings.
 */
public interface ChatConversationAiSettingUseCase {

    ChatConversationAiSetting create(ChatConversationAiSettingCommand command);

    ChatConversationAiSetting getById(UUID id);

    List<ChatConversationAiSetting> getAll();

    ChatConversationAiSetting update(UUID id, ChatConversationAiSettingCommand command);

    void delete(UUID id);
}
