package com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatescalationstatushistory.exceptions.ChatEscalationStatusHistoryNotFoundException;
import com.mindconnect.infrastructure.chatescalationstatushistory.adapters.in.rest.controllers.ChatEscalationStatusHistoryController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_escalation_status_history en una respuesta 404.
 * Solo aplica a ChatEscalationStatusHistoryController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatEscalationStatusHistoryController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatEscalationStatusHistoryExceptionHandler {

    @ExceptionHandler(ChatEscalationStatusHistoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatEscalationStatusHistoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
