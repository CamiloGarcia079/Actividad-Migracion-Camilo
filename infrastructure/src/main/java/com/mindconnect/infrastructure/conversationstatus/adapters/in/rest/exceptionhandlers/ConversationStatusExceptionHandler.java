package com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.conversationstatus.exceptions.ConversationStatusNotFoundException;
import com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.controllers.ConversationStatusController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de conversations_statuses en una respuesta 404.
 * Solo aplica a ConversationStatusController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ConversationStatusController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ConversationStatusExceptionHandler {

    @ExceptionHandler(ConversationStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ConversationStatusNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
