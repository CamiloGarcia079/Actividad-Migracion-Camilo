package com.mindconnect.application.treatmentplan.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de treatment_plans con el id pedido.
 */
public class TreatmentPlanNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public TreatmentPlanNotFoundException(UUID id) {
        super("No se encontró un registro de treatment_plans con id " + id);
    }
}
