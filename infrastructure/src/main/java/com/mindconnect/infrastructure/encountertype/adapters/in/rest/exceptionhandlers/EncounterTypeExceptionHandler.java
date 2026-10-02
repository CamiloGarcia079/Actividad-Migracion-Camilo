package com.mindconnect.infrastructure.encountertype.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.encountertype.exceptions.EncounterTypeNotFoundException;
import com.mindconnect.infrastructure.encountertype.adapters.in.rest.controllers.EncounterTypeController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de encounter_types en una respuesta 404.
 * Solo aplica a EncounterTypeController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = EncounterTypeController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncounterTypeExceptionHandler {

    @ExceptionHandler(EncounterTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EncounterTypeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
