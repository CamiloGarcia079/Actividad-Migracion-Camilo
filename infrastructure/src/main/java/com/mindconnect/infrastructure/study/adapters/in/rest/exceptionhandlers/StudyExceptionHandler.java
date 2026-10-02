package com.mindconnect.infrastructure.study.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.study.exceptions.StudyNotFoundException;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;
import com.mindconnect.infrastructure.study.adapters.in.rest.controllers.StudyController;

/**
 * Convierte el "no encontrado" de studies en una respuesta 404.
 * Solo aplica a StudyController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = StudyController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StudyExceptionHandler {

    @ExceptionHandler(StudyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(StudyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
