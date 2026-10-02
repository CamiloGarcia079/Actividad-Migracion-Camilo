package com.mindconnect.application.relationshiptype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de relationship_types con el id pedido.
 */
public class RelationshipTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RelationshipTypeNotFoundException(UUID id) {
        super("No se encontró un registro de relationship_types con id " + id);
    }
}
