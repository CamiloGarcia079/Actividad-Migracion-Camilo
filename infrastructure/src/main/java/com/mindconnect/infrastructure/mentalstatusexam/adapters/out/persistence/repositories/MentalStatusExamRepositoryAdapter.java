package com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.repositories.MentalStatusExamRepository;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto MentalStatusExamRepository del dominio usando JPA.
 */
@Component
public class MentalStatusExamRepositoryAdapter implements MentalStatusExamRepository {

    private final SpringDataMentalStatusExamJpaRepository jpaRepository;
    private final MentalStatusExamPersistenceMapper mapper;

    public MentalStatusExamRepositoryAdapter(SpringDataMentalStatusExamJpaRepository jpaRepository,
                                     MentalStatusExamPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MentalStatusExam save(MentalStatusExam mentalStatusExam) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(mentalStatusExam)));
    }

    @Override
    public Optional<MentalStatusExam> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<MentalStatusExam> findAll() {
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
