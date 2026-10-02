package com.mindconnect.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.priority.model.aggregates.Priority;
import com.mindconnect.domain.priority.repositories.PriorityRepository;
import com.mindconnect.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto PriorityRepository del dominio usando JPA.
 */
@Component
public class PriorityRepositoryAdapter implements PriorityRepository {

    private final SpringDataPriorityJpaRepository jpaRepository;
    private final PriorityPersistenceMapper mapper;

    public PriorityRepositoryAdapter(SpringDataPriorityJpaRepository jpaRepository,
                                     PriorityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Priority save(Priority priority) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(priority)));
    }

    @Override
    public Optional<Priority> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Priority> findAll() {
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
