package com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;
import com.mindconnect.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de relationship_types.
 */
@Component
public class RelationshipTypePersistenceMapper {

    public RelationshipTypeJpaEntity toEntity(RelationshipType relationshipType) {
        RelationshipTypeJpaEntity entity = new RelationshipTypeJpaEntity();
        entity.setId(relationshipType.id());
        entity.setDescription(relationshipType.description());
        return entity;
    }

    public RelationshipType toDomain(RelationshipTypeJpaEntity entity) {
        return new RelationshipType(
                entity.getId(),
                entity.getDescription());
    }
}
