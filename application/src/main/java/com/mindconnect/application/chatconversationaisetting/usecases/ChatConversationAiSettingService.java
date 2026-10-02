package com.mindconnect.application.chatconversationaisetting.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.chatconversationaisetting.dtos.ChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.exceptions.ChatConversationAiSettingNotFoundException;
import com.mindconnect.application.chatconversationaisetting.ports.in.ChatConversationAiSettingUseCase;
import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.repositories.ChatConversationAiSettingRepository;

/**
 * Implementación de los casos de uso de chat_conversation_ai_settings.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class ChatConversationAiSettingService implements ChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;

    public ChatConversationAiSettingService(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatConversationAiSetting create(ChatConversationAiSettingCommand command) {
        LocalDateTime now = LocalDateTime.now();
        ChatConversationAiSetting chatConversationAiSetting = new ChatConversationAiSetting(
                UUID.randomUUID(),
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId(),
                now,
                now);
        return repository.save(chatConversationAiSetting);
    }

    @Override
    public ChatConversationAiSetting getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundException(id));
    }

    @Override
    public List<ChatConversationAiSetting> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatConversationAiSetting update(UUID id, ChatConversationAiSettingCommand command) {
        ChatConversationAiSetting existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        ChatConversationAiSetting updated = new ChatConversationAiSetting(
                existing.id(),
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ChatConversationAiSettingNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
