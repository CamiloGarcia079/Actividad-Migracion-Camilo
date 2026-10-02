package com.mindconnect.infrastructure.professional.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.professional.exceptions.ProfessionalNotFoundException;
import com.mindconnect.infrastructure.professional.adapters.in.rest.controllers.ProfessionalController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de professionals en una respuesta 404.
 * Solo aplica a ProfessionalController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ProfessionalController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProfessionalExceptionHandler {

    @ExceptionHandler(ProfessionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProfessionalNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
