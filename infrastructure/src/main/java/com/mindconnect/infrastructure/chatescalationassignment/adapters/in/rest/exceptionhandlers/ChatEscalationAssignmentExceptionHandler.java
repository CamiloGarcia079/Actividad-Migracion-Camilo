package com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatescalationassignment.exceptions.ChatEscalationAssignmentNotFoundException;
import com.mindconnect.infrastructure.chatescalationassignment.adapters.in.rest.controllers.ChatEscalationAssignmentController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_escalation_assignments en una respuesta 404.
 * Solo aplica a ChatEscalationAssignmentController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatEscalationAssignmentController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatEscalationAssignmentExceptionHandler {

    @ExceptionHandler(ChatEscalationAssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatEscalationAssignmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
