package com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.repositories.TreatmentPlanRepository;
import com.mindconnect.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto TreatmentPlanRepository del dominio usando JPA.
 */
@Component
public class TreatmentPlanRepositoryAdapter implements TreatmentPlanRepository {

    private final SpringDataTreatmentPlanJpaRepository jpaRepository;
    private final TreatmentPlanPersistenceMapper mapper;

    public TreatmentPlanRepositoryAdapter(SpringDataTreatmentPlanJpaRepository jpaRepository,
                                     TreatmentPlanPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentPlan save(TreatmentPlan treatmentPlan) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(treatmentPlan)));
    }

    @Override
    public Optional<TreatmentPlan> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentPlan> findAll() {
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
