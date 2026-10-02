package com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.repositories.ChatAiRunMetricRepository;
import com.mindconnect.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ChatAiRunMetricRepository del dominio usando JPA.
 */
@Component
public class ChatAiRunMetricRepositoryAdapter implements ChatAiRunMetricRepository {

    private final SpringDataChatAiRunMetricJpaRepository jpaRepository;
    private final ChatAiRunMetricPersistenceMapper mapper;

    public ChatAiRunMetricRepositoryAdapter(SpringDataChatAiRunMetricJpaRepository jpaRepository,
                                     ChatAiRunMetricPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunMetric save(ChatAiRunMetric chatAiRunMetric) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(chatAiRunMetric)));
    }

    @Override
    public Optional<ChatAiRunMetric> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunMetric> findAll() {
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
