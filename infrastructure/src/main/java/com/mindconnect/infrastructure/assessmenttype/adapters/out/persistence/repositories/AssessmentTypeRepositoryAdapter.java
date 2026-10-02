package com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;
import com.mindconnect.domain.assessmenttype.repositories.AssessmentTypeRepository;
import com.mindconnect.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto AssessmentTypeRepository del dominio usando JPA.
 */
@Component
public class AssessmentTypeRepositoryAdapter implements AssessmentTypeRepository {

    private final SpringDataAssessmentTypeJpaRepository jpaRepository;
    private final AssessmentTypePersistenceMapper mapper;

    public AssessmentTypeRepositoryAdapter(SpringDataAssessmentTypeJpaRepository jpaRepository,
                                     AssessmentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AssessmentType save(AssessmentType assessmentType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(assessmentType)));
    }

    @Override
    public Optional<AssessmentType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AssessmentType> findAll() {
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
