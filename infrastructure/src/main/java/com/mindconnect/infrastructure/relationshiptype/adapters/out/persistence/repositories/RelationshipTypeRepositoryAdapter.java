package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;
import com.mindconnect.domain.relationshiptype.repositories.RelationshipTypeRepository;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.mappers.RelationshipTypePersistenceMapper;

/**
 * Adaptador de salida: implementa el puerto RelationshipTypeRepository del dominio usando JPA.
 */
@Component
public class RelationshipTypeRepositoryAdapter implements RelationshipTypeRepository {

    private final SpringDataRelationshipTypeJpaRepository jpaRepository;
    private final RelationshipTypePersistenceMapper mapper;

    public RelationshipTypeRepositoryAdapter(SpringDataRelationshipTypeJpaRepository jpaRepository,
                                     RelationshipTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RelationshipType save(RelationshipType relationshipType) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(relationshipType)));
    }

    @Override
    public Optional<RelationshipType> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<RelationshipType> findAll() {
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
