package com.mindconnect.application.relationshiptype.usecases;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.relationshiptype.dtos.RelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.exceptions.RelationshipTypeNotFoundException;
import com.mindconnect.application.relationshiptype.ports.in.RelationshipTypeUseCase;
import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;
import com.mindconnect.domain.relationshiptype.repositories.RelationshipTypeRepository;

/**
 * Implementación de los casos de uso de relationship_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class RelationshipTypeService implements RelationshipTypeUseCase {

    private final RelationshipTypeRepository repository;

    public RelationshipTypeService(RelationshipTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public RelationshipType create(RelationshipTypeCommand command) {
        RelationshipType relationshipType = new RelationshipType(
                UUID.randomUUID(),
                command.description());
        return repository.save(relationshipType);
    }

    @Override
    public RelationshipType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RelationshipTypeNotFoundException(id));
    }

    @Override
    public List<RelationshipType> getAll() {
        return repository.findAll();
    }

    @Override
    public RelationshipType update(UUID id, RelationshipTypeCommand command) {
        RelationshipType existing = getById(id);
        RelationshipType updated = new RelationshipType(
                existing.id(),
                command.description());
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RelationshipTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
