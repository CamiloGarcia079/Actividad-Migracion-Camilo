package com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.encounterstatus.exceptions.EncounterStatusNotFoundException;
import com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.controllers.EncounterStatusController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de encounter_statuses en una respuesta 404.
 * Solo aplica a EncounterStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EncounterStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncounterStatusExceptionHandler {

    @ExceptionHandler(EncounterStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EncounterStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
