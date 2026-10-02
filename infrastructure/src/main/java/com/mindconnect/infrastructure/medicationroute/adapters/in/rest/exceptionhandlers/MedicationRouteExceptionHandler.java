package com.mindconnect.infrastructure.medicationroute.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.medicationroute.exceptions.MedicationRouteNotFoundException;
import com.mindconnect.infrastructure.medicationroute.adapters.in.rest.controllers.MedicationRouteController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de medication_routes en una respuesta 404.
 * Solo aplica a MedicationRouteController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = MedicationRouteController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MedicationRouteExceptionHandler {

    @ExceptionHandler(MedicationRouteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(MedicationRouteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
