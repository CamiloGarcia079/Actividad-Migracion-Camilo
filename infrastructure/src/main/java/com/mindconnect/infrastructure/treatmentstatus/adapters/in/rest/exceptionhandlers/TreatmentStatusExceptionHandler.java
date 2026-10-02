package com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.treatmentstatus.exceptions.TreatmentStatusNotFoundException;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;
import com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.controllers.TreatmentStatusController;

/**
 * Convierte el "no encontrado" de treatment_statuses en una respuesta 404.
 * Solo aplica a TreatmentStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = TreatmentStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TreatmentStatusExceptionHandler {

    @ExceptionHandler(TreatmentStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TreatmentStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
