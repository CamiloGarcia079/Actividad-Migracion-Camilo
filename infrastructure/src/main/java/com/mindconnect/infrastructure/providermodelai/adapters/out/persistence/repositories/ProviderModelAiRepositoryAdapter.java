package com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;
import com.mindconnect.domain.providermodelai.repositories.ProviderModelAiRepository;
import com.mindconnect.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ProviderModelAiRepository del dominio usando JPA.
 */
@Component
public class ProviderModelAiRepositoryAdapter implements ProviderModelAiRepository {

    private final SpringDataProviderModelAiJpaRepository jpaRepository;
    private final ProviderModelAiPersistenceMapper mapper;

    public ProviderModelAiRepositoryAdapter(SpringDataProviderModelAiJpaRepository jpaRepository,
                                     ProviderModelAiPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProviderModelAi save(ProviderModelAi providerModelAi) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(providerModelAi)));
    }

    @Override
    public Optional<ProviderModelAi> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProviderModelAi> findAll() {
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
