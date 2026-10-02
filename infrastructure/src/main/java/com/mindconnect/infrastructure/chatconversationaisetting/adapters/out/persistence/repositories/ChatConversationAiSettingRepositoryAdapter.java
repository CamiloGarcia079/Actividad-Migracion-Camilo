package com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.repositories.ChatConversationAiSettingRepository;
import com.mindconnect.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatConversationAiSettingRepository del dominio usando JPA.
 */
@Component
public class ChatConversationAiSettingRepositoryAdapter implements ChatConversationAiSettingRepository {

    private final SpringDataChatConversationAiSettingJpaRepository jpaRepository;
    private final ChatConversationAiSettingPersistenceMapper mapper;

    public ChatConversationAiSettingRepositoryAdapter(SpringDataChatConversationAiSettingJpaRepository jpaRepository,
                                     ChatConversationAiSettingPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversationAiSetting save(ChatConversationAiSetting chatConversationAiSetting) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatConversationAiSetting)));
    }

    @Override
    public Optional<ChatConversationAiSetting> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversationAiSetting> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
