package com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.clinicalnote.exceptions.ClinicalNoteNotFoundException;
import com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.controllers.ClinicalNoteController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de clinical_notes en una respuesta 404.
 * Solo aplica a ClinicalNoteController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ClinicalNoteController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClinicalNoteExceptionHandler {

    @ExceptionHandler(ClinicalNoteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ClinicalNoteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
