package com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.treatmentplan.exceptions.TreatmentPlanNotFoundException;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;
import com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.controllers.TreatmentPlanController;

/**
 * Convierte el "no encontrado" de treatment_plans en una respuesta 404.
 * Solo aplica a TreatmentPlanController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = TreatmentPlanController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TreatmentPlanExceptionHandler {

    @ExceptionHandler(TreatmentPlanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TreatmentPlanNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
