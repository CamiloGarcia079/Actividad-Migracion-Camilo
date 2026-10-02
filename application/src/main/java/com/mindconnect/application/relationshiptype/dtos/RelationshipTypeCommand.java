package com.mindconnect.application.relationshiptype.dtos;

/**
 * Datos que entran al caso de uso de relationship_types.
 * No lleva id ni fechas de auditoría: las asigna el servicio.
 */
public record RelationshipTypeCommand(
        String description) {
}
