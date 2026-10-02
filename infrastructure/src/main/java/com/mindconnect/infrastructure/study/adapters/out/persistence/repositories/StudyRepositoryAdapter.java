package com.mindconnect.infrastructure.study.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.study.model.aggregates.Study;
import com.mindconnect.domain.study.repositories.StudyRepository;
import com.mindconnect.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto StudyRepository del dominio usando JPA.
 */
@Component
public class StudyRepositoryAdapter implements StudyRepository {

    private final SpringDataStudyJpaRepository jpaRepository;
    private final StudyPersistenceMapper mapper;

    public StudyRepositoryAdapter(SpringDataStudyJpaRepository jpaRepository,
                                     StudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Study save(Study study) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(study)));
    }

    @Override
    public Optional<Study> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Study> findAll() {
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
