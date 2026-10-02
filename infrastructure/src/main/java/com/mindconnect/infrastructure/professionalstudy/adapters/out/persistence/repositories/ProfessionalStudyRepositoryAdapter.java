package com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.repositories.ProfessionalStudyRepository;
import com.mindconnect.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ProfessionalStudyRepository del dominio usando JPA.
 */
@Component
public class ProfessionalStudyRepositoryAdapter implements ProfessionalStudyRepository {

    private final SpringDataProfessionalStudyJpaRepository jpaRepository;
    private final ProfessionalStudyPersistenceMapper mapper;

    public ProfessionalStudyRepositoryAdapter(SpringDataProfessionalStudyJpaRepository jpaRepository,
                                     ProfessionalStudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalStudy save(ProfessionalStudy professionalStudy) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(professionalStudy)));
    }

    @Override
    public Optional<ProfessionalStudy> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalStudy> findAll() {
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
