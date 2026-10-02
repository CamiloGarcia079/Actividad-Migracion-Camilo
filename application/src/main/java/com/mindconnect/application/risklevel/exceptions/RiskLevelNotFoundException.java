package com.mindconnect.application.risklevel.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de risk_levels con el id pedido.
 */
public class RiskLevelNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RiskLevelNotFoundException(UUID id) {
        super("No se encontró un registro de risk_levels con id " + id);
    }
}
