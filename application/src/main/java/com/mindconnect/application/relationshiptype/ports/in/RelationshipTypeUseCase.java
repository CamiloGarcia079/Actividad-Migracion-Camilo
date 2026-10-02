package com.mindconnect.application.relationshiptype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.relationshiptype.dtos.RelationshipTypeCommand;
import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre relationship_types.
 */
public interface RelationshipTypeUseCase {

    RelationshipType create(RelationshipTypeCommand command);

    RelationshipType getById(UUID id);

    List<RelationshipType> getAll();

    RelationshipType update(UUID id, RelationshipTypeCommand command);

    void delete(UUID id);
}
