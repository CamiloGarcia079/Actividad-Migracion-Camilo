package com.mindconnect.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encounter.model.aggregates.Encounter;
import com.mindconnect.domain.encounter.repositories.EncounterRepository;
import com.mindconnect.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EncounterRepository del dominio usando JPA.
 */
@Component
public class EncounterRepositoryAdapter implements EncounterRepository {

    private final SpringDataEncounterJpaRepository jpaRepository;
    private final EncounterPersistenceMapper mapper;

    public EncounterRepositoryAdapter(SpringDataEncounterJpaRepository jpaRepository,
                                     EncounterPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Encounter save(Encounter encounter) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(encounter)));
    }

    @Override
    public Optional<Encounter> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Encounter> findAll() {
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
