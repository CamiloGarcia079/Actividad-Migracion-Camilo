package com.mindconnect.domain.relationshiptype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar relationship_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface RelationshipTypeRepository {

    RelationshipType save(RelationshipType relationshipType);

    Optional<RelationshipType> findById(UUID id);

    List<RelationshipType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
