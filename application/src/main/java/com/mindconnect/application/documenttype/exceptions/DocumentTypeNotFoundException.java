package com.mindconnect.application.documenttype.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de document_types con el id pedido.
 */
public class DocumentTypeNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DocumentTypeNotFoundException(UUID id) {
        super("No se encontró un registro de document_types con id " + id);
    }
}
