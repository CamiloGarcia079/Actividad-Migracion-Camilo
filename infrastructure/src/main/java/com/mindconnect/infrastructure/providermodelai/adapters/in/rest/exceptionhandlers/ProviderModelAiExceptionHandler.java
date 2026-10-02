package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.providermodelai.exceptions.ProviderModelAiNotFoundException;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.controllers.ProviderModelAiController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de provider_models_ai en una respuesta 404.
 * Solo aplica a ProviderModelAiController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ProviderModelAiController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProviderModelAiExceptionHandler {

    @ExceptionHandler(ProviderModelAiNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ProviderModelAiNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
