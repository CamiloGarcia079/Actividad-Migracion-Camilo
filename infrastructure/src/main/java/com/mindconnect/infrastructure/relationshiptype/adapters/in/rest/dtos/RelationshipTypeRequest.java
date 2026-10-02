package com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.mindconnect.application.relationshiptype.dtos.RelationshipTypeCommand;

/**
 * Cuerpo JSON que recibe la API para crear o actualizar un registro de relationship_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record RelationshipTypeRequest(
        @NotBlank @Size(max = 50) String description) {

    public RelationshipTypeCommand toCommand() {
        return new RelationshipTypeCommand(
                description);
    }
}
