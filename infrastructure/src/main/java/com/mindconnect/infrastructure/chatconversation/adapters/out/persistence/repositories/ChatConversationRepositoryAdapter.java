package com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;
import com.mindconnect.domain.chatconversation.repositories.ChatConversationRepository;
import com.mindconnect.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatConversationRepository del dominio usando JPA.
 */
@Component
public class ChatConversationRepositoryAdapter implements ChatConversationRepository {

    private final SpringDataChatConversationJpaRepository jpaRepository;
    private final ChatConversationPersistenceMapper mapper;

    public ChatConversationRepositoryAdapter(SpringDataChatConversationJpaRepository jpaRepository,
                                     ChatConversationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversation save(ChatConversation chatConversation) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatConversation)));
    }

    @Override
    public Optional<ChatConversation> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversation> findAll() {
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
