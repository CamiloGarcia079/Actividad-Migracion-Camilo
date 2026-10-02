package com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;
import com.mindconnect.domain.chatmessage.repositories.ChatMessageRepository;
import com.mindconnect.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatMessageRepository del dominio usando JPA.
 */
@Component
public class ChatMessageRepositoryAdapter implements ChatMessageRepository {

    private final SpringDataChatMessageJpaRepository jpaRepository;
    private final ChatMessagePersistenceMapper mapper;

    public ChatMessageRepositoryAdapter(SpringDataChatMessageJpaRepository jpaRepository,
                                     ChatMessagePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatMessage)));
    }

    @Override
    public Optional<ChatMessage> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatMessage> findAll() {
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
