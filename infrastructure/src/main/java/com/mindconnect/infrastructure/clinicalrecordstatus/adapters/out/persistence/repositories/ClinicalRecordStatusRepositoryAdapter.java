package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.repositories.ClinicalRecordStatusRepository;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ClinicalRecordStatusRepository del dominio usando JPA.
 */
@Component
public class ClinicalRecordStatusRepositoryAdapter implements ClinicalRecordStatusRepository {

    private final SpringDataClinicalRecordStatusJpaRepository jpaRepository;
    private final ClinicalRecordStatusPersistenceMapper mapper;

    public ClinicalRecordStatusRepositoryAdapter(SpringDataClinicalRecordStatusJpaRepository jpaRepository,
                                     ClinicalRecordStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(clinicalRecordStatus)));
    }

    @Override
    public Optional<ClinicalRecordStatus> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalRecordStatus> findAll() {
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
