package com.mindconnect.application.riskassessment.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de risk_assessments con el id pedido.
 */
public class RiskAssessmentNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public RiskAssessmentNotFoundException(UUID id) {
        super("No se encontró un registro de risk_assessments con id " + id);
    }
}
