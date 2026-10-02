package com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;
import com.mindconnect.domain.encountermodality.repositories.EncounterModalityRepository;
import com.mindconnect.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto EncounterModalityRepository del dominio usando JPA.
 */
@Component
public class EncounterModalityRepositoryAdapter implements EncounterModalityRepository {

    private final SpringDataEncounterModalityJpaRepository jpaRepository;
    private final EncounterModalityPersistenceMapper mapper;

    public EncounterModalityRepositoryAdapter(SpringDataEncounterModalityJpaRepository jpaRepository,
                                     EncounterModalityPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EncounterModality save(EncounterModality encounterModality) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(encounterModality)));
    }

    @Override
    public Optional<EncounterModality> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<EncounterModality> findAll() {
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
