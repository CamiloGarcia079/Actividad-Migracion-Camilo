package com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;
import com.mindconnect.domain.riskassessment.repositories.RiskAssessmentRepository;
import com.mindconnect.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto RiskAssessmentRepository del dominio usando JPA.
 */
@Component
public class RiskAssessmentRepositoryAdapter implements RiskAssessmentRepository {

    private final SpringDataRiskAssessmentJpaRepository jpaRepository;
    private final RiskAssessmentPersistenceMapper mapper;

    public RiskAssessmentRepositoryAdapter(SpringDataRiskAssessmentJpaRepository jpaRepository,
                                     RiskAssessmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskAssessment save(RiskAssessment riskAssessment) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(riskAssessment)));
    }

    @Override
    public Optional<RiskAssessment> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<RiskAssessment> findAll() {
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
