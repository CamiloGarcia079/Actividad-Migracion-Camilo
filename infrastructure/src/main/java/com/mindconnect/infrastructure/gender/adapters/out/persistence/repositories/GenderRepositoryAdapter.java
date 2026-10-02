package com.mindconnect.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.gender.model.aggregates.Gender;
import com.mindconnect.domain.gender.repositories.GenderRepository;
import com.mindconnect.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto GenderRepository del dominio usando JPA.
 */
@Component
public class GenderRepositoryAdapter implements GenderRepository {

    private final SpringDataGenderJpaRepository jpaRepository;
    private final GenderPersistenceMapper mapper;

    public GenderRepositoryAdapter(SpringDataGenderJpaRepository jpaRepository,
                                     GenderPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Gender save(Gender gender) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(gender)));
    }

    @Override
    public Optional<Gender> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Gender> findAll() {
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
