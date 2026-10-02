package com.mindconnect.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;
import com.mindconnect.domain.stateregion.repositories.StateRegionRepository;
import com.mindconnect.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto StateRegionRepository del dominio usando JPA.
 */
@Component
public class StateRegionRepositoryAdapter implements StateRegionRepository {

    private final SpringDataStateRegionJpaRepository jpaRepository;
    private final StateRegionPersistenceMapper mapper;

    public StateRegionRepositoryAdapter(SpringDataStateRegionJpaRepository jpaRepository,
                                     StateRegionPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public StateRegion save(StateRegion stateRegion) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(stateRegion)));
    }

    @Override
    public Optional<StateRegion> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<StateRegion> findAll() {
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
