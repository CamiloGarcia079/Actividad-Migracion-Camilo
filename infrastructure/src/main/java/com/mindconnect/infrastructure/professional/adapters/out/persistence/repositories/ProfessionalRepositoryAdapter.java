package com.mindconnect.infrastructure.professional.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.professional.model.aggregates.Professional;
import com.mindconnect.domain.professional.repositories.ProfessionalRepository;
import com.mindconnect.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto ProfessionalRepository del dominio usando JPA.
 */
@Component
public class ProfessionalRepositoryAdapter implements ProfessionalRepository {

    private final SpringDataProfessionalJpaRepository jpaRepository;
    private final ProfessionalPersistenceMapper mapper;

    public ProfessionalRepositoryAdapter(SpringDataProfessionalJpaRepository jpaRepository,
                                     ProfessionalPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Professional save(Professional professional) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(professional)));
    }

    @Override
    public Optional<Professional> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Professional> findAll() {
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
