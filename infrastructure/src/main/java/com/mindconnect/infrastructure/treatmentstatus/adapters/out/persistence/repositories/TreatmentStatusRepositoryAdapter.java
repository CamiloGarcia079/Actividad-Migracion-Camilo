package com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.repositories.TreatmentStatusRepository;
import com.mindconnect.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto TreatmentStatusRepository del dominio usando JPA.
 */
@Component
public class TreatmentStatusRepositoryAdapter implements TreatmentStatusRepository {

    private final SpringDataTreatmentStatusJpaRepository jpaRepository;
    private final TreatmentStatusPersistenceMapper mapper;

    public TreatmentStatusRepositoryAdapter(SpringDataTreatmentStatusJpaRepository jpaRepository,
                                     TreatmentStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentStatus save(TreatmentStatus treatmentStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(treatmentStatus)));
    }

    @Override
    public Optional<TreatmentStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<TreatmentStatus> findAll() {
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
