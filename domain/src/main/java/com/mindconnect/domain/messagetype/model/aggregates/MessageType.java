package com.mindconnect.domain.messagetype.model.aggregates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla message_types.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record MessageType(
        UUID id,
        String nameType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public MessageType {
        DomainValidations.required(id, "id");
        DomainValidations.required(nameType, "nameType");
        DomainValidations.required(createdAt, "createdAt");
        DomainValidations.required(updatedAt, "updatedAt");
    }
}
