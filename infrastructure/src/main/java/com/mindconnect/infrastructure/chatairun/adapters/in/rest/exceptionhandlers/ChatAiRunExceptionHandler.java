package com.mindconnect.infrastructure.chatairun.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.chatairun.exceptions.ChatAiRunNotFoundException;
import com.mindconnect.infrastructure.chatairun.adapters.in.rest.controllers.ChatAiRunController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de chat_ai_runs en una respuesta 404.
 * Solo aplica a ChatAiRunController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ChatAiRunController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChatAiRunExceptionHandler {

    @ExceptionHandler(ChatAiRunNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ChatAiRunNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
