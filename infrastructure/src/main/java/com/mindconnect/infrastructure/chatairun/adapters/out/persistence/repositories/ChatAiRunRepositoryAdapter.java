package com.mindconnect.infrastructure.chatairun.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;
import com.mindconnect.domain.chatairun.repositories.ChatAiRunRepository;
import com.mindconnect.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatAiRunRepository del dominio usando JPA.
 */
@Component
public class ChatAiRunRepositoryAdapter implements ChatAiRunRepository {

    private final SpringDataChatAiRunJpaRepository jpaRepository;
    private final ChatAiRunPersistenceMapper mapper;

    public ChatAiRunRepositoryAdapter(SpringDataChatAiRunJpaRepository jpaRepository,
                                     ChatAiRunPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRun save(ChatAiRun chatAiRun) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatAiRun)));
    }

    @Override
    public Optional<ChatAiRun> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRun> findAll() {
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
