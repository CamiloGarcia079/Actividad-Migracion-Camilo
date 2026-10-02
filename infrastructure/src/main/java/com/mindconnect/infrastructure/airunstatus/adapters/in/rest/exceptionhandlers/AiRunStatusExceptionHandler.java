package com.mindconnect.infrastructure.airunstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.airunstatus.exceptions.AiRunStatusNotFoundException;
import com.mindconnect.infrastructure.airunstatus.adapters.in.rest.controllers.AiRunStatusController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de ai_runs_statuses en una respuesta 404.
 * Solo aplica a AiRunStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = AiRunStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AiRunStatusExceptionHandler {

    @ExceptionHandler(AiRunStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AiRunStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
