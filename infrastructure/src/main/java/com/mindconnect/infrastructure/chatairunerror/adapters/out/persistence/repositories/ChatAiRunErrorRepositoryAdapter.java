package com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.repositories.ChatAiRunErrorRepository;
import com.mindconnect.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatAiRunErrorRepository del dominio usando JPA.
 */
@Component
public class ChatAiRunErrorRepositoryAdapter implements ChatAiRunErrorRepository {

    private final SpringDataChatAiRunErrorJpaRepository jpaRepository;
    private final ChatAiRunErrorPersistenceMapper mapper;

    public ChatAiRunErrorRepositoryAdapter(SpringDataChatAiRunErrorJpaRepository jpaRepository,
                                     ChatAiRunErrorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunError save(ChatAiRunError chatAiRunError) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatAiRunError)));
    }

    @Override
    public Optional<ChatAiRunError> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunError> findAll() {
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
