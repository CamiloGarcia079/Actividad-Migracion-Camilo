package com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;
import com.mindconnect.domain.clinicalnote.repositories.ClinicalNoteRepository;
import com.mindconnect.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ClinicalNoteRepository del dominio usando JPA.
 */
@Component
public class ClinicalNoteRepositoryAdapter implements ClinicalNoteRepository {

    private final SpringDataClinicalNoteJpaRepository jpaRepository;
    private final ClinicalNotePersistenceMapper mapper;

    public ClinicalNoteRepositoryAdapter(SpringDataClinicalNoteJpaRepository jpaRepository,
                                     ClinicalNotePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ClinicalNote save(ClinicalNote clinicalNote) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(clinicalNote)));
    }

    @Override
    public Optional<ClinicalNote> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ClinicalNote> findAll() {
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
