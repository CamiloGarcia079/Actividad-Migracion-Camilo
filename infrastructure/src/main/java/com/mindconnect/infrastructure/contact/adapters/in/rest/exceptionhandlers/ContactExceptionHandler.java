package com.mindconnect.infrastructure.contact.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.contact.exceptions.ContactNotFoundException;
import com.mindconnect.infrastructure.contact.adapters.in.rest.controllers.ContactController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de contacts en una respuesta 404.
 * Solo aplica a ContactController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = ContactController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContactExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ContactNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
