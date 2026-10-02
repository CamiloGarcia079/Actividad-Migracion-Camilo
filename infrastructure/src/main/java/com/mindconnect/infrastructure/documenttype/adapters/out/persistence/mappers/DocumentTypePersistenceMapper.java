package com.mindconnect.infrastructure.documenttype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;
import com.mindconnect.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de document_types.
 */
@Component
public class DocumentTypePersistenceMapper {

    public DocumentTypeJpaEntity toEntity(DocumentType documentType) {
        DocumentTypeJpaEntity entity = new DocumentTypeJpaEntity();
        entity.setId(documentType.id());
        entity.setCode(documentType.code());
        entity.setName(documentType.name());
        entity.setActive(documentType.active());
        entity.setCreatedAt(documentType.createdAt());
        entity.setUpdatedAt(documentType.updatedAt());
        return entity;
    }

    public DocumentType toDomain(DocumentTypeJpaEntity entity) {
        return new DocumentType(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
