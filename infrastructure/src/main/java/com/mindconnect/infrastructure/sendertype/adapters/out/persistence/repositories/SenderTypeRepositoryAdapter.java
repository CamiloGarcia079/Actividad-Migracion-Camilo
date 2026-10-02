package com.mindconnect.infrastructure.sendertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.sendertype.model.aggregates.SenderType;
import com.mindconnect.domain.sendertype.repositories.SenderTypeRepository;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto SenderTypeRepository del dominio usando JPA.
 */
@Component
public class SenderTypeRepositoryAdapter implements SenderTypeRepository {

    private final SpringDataSenderTypeJpaRepository jpaRepository;
    private final SenderTypePersistenceMapper mapper;

    public SenderTypeRepositoryAdapter(SpringDataSenderTypeJpaRepository jpaRepository,
                                     SenderTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SenderType save(SenderType senderType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(senderType)));
    }

    @Override
    public Optional<SenderType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SenderType> findAll() {
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
