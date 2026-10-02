package com.mindconnect.application.diagnosticsystem.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de diagnostic_systems con el id pedido.
 */
public class DiagnosticSystemNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public DiagnosticSystemNotFoundException(UUID id) {
        super("No se encontró un registro de diagnostic_systems con id " + id);
    }
}
