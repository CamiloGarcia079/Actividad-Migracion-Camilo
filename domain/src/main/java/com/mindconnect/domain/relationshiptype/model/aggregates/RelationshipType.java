package com.mindconnect.domain.relationshiptype.model.aggregates;

import java.util.UUID;

import com.mindconnect.domain.shared.validation.DomainValidations;

/**
 * Modelo de dominio de la tabla relationship_types.
 *
 * <p>Es un record: inmutable y sin anotaciones de Spring ni de JPA. Los campos
 * obligatorios (los NOT NULL de la tabla) se validan en el constructor.</p>
 */
public record RelationshipType(
        UUID id,
        String description) {

    public RelationshipType {
        DomainValidations.required(id, "id");
        DomainValidations.required(description, "description");
    }
}
