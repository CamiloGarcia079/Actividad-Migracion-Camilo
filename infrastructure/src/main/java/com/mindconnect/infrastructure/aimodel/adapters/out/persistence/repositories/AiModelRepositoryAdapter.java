package com.mindconnect.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.aimodel.model.aggregates.AiModel;
import com.mindconnect.domain.aimodel.repositories.AiModelRepository;
import com.mindconnect.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto AiModelRepository del dominio usando JPA.
 */
@Component
public class AiModelRepositoryAdapter implements AiModelRepository {

    private final SpringDataAiModelJpaRepository jpaRepository;
    private final AiModelPersistenceMapper mapper;

    public AiModelRepositoryAdapter(SpringDataAiModelJpaRepository jpaRepository,
                                     AiModelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AiModel save(AiModel aiModel) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(aiModel)));
    }

    @Override
    public Optional<AiModel> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AiModel> findAll() {
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
