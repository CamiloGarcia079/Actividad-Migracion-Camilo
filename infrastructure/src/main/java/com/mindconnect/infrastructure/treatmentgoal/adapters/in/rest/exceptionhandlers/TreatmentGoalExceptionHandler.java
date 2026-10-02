package com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.treatmentgoal.exceptions.TreatmentGoalNotFoundException;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;
import com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.controllers.TreatmentGoalController;

/**
 * Convierte el "no encontrado" de treatment_goals en una respuesta 404.
 * Solo aplica a TreatmentGoalController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = TreatmentGoalController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TreatmentGoalExceptionHandler {

    @ExceptionHandler(TreatmentGoalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TreatmentGoalNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
