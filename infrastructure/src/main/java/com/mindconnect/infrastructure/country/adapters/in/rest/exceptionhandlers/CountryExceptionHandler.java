package com.mindconnect.infrastructure.country.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.country.exceptions.CountryNotFoundException;
import com.mindconnect.infrastructure.country.adapters.in.rest.controllers.CountryController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de countries en una respuesta 404.
 * Solo aplica a CountryController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = CountryController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CountryExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(CountryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
