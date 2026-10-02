package com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.airunstatus.model.aggregates.AiRunStatus;
import com.mindconnect.domain.airunstatus.repositories.AiRunStatusRepository;
import com.mindconnect.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto AiRunStatusRepository del dominio usando JPA.
 */
@Component
public class AiRunStatusRepositoryAdapter implements AiRunStatusRepository {

    private final SpringDataAiRunStatusJpaRepository jpaRepository;
    private final AiRunStatusPersistenceMapper mapper;

    public AiRunStatusRepositoryAdapter(SpringDataAiRunStatusJpaRepository jpaRepository,
                                     AiRunStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiRunStatus save(AiRunStatus aiRunStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(aiRunStatus)));
    }

    @Override
    public Optional<AiRunStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AiRunStatus> findAll() {
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
