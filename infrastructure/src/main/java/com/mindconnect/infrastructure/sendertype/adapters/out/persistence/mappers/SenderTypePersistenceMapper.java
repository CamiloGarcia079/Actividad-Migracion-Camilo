package com.mindconnect.infrastructure.sendertype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.sendertype.model.aggregates.SenderType;
import com.mindconnect.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de sender_types.
 */
@Component
public class SenderTypePersistenceMapper {

    public SenderTypeJpaEntity toEntity(SenderType senderType) {
        SenderTypeJpaEntity entity = new SenderTypeJpaEntity();
        entity.setId(senderType.id());
        entity.setNameType(senderType.nameType());
        entity.setCreatedAt(senderType.createdAt());
        entity.setUpdatedAt(senderType.updatedAt());
        return entity;
    }

    public SenderType toDomain(SenderTypeJpaEntity entity) {
        return new SenderType(
                entity.getId(),
                entity.getNameType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
