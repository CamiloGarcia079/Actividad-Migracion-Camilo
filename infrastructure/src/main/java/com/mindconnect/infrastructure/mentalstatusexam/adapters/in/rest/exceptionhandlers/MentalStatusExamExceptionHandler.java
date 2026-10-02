package com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.mentalstatusexam.exceptions.MentalStatusExamNotFoundException;
import com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.controllers.MentalStatusExamController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de mental_status_exams en una respuesta 404.
 * Solo aplica a MentalStatusExamController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = MentalStatusExamController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MentalStatusExamExceptionHandler {

    @ExceptionHandler(MentalStatusExamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(MentalStatusExamNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
