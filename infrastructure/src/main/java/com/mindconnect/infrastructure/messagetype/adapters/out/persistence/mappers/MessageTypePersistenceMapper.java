package com.mindconnect.infrastructure.messagetype.adapters.out.persistence.mappers;

import org.springframework.stereotype.Component;

import com.mindconnect.domain.messagetype.model.aggregates.MessageType;
import com.mindconnect.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

/**
 * Convierte entre el modelo de dominio y la entidad JPA de message_types.
 */
@Component
public class MessageTypePersistenceMapper {

    public MessageTypeJpaEntity toEntity(MessageType messageType) {
        MessageTypeJpaEntity entity = new MessageTypeJpaEntity();
        entity.setId(messageType.id());
        entity.setNameType(messageType.nameType());
        entity.setCreatedAt(messageType.createdAt());
        entity.setUpdatedAt(messageType.updatedAt());
        return entity;
    }

    public MessageType toDomain(MessageTypeJpaEntity entity) {
        return new MessageType(
                entity.getId(),
                entity.getNameType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
