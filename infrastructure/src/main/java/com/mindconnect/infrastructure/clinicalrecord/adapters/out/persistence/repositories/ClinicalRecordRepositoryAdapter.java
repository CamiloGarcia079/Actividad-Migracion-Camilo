package com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.repositories.ClinicalRecordRepository;
import com.mindconnect.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ClinicalRecordRepository del dominio usando JPA.
 */
@Component
public class ClinicalRecordRepositoryAdapter implements ClinicalRecordRepository {

    private final SpringDataClinicalRecordJpaRepository jpaRepository;
    private final ClinicalRecordPersistenceMapper mapper;

    public ClinicalRecordRepositoryAdapter(SpringDataClinicalRecordJpaRepository jpaRepository,
                                     ClinicalRecordPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalRecord save(ClinicalRecord clinicalRecord) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(clinicalRecord)));
    }

    @Override
    public Optional<ClinicalRecord> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalRecord> findAll() {
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
