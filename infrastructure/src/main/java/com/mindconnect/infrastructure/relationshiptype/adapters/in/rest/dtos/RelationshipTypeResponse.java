package com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.dtos;

import java.util.UUID;

import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;

/**
 * Respuesta JSON de la API para un registro de relationship_types.
 */
public record RelationshipTypeResponse(
        UUID id,
        String description) {

    public static RelationshipTypeResponse from(RelationshipType relationshipType) {
        return new RelationshipTypeResponse(
                relationshipType.id(), relationshipType.description());
    }
}
