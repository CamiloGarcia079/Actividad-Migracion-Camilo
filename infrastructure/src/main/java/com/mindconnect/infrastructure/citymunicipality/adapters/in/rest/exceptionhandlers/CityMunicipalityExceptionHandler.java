package com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.exceptionhandlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindconnect.application.citymunicipality.exceptions.CityMunicipalityNotFoundException;
import com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.controllers.CityMunicipalityController;
import com.mindconnect.infrastructure.shared.dtos.ErrorResponse;

/**
 * Convierte el "no encontrado" de city_municipalities en una respuesta 404.
 * Solo aplica a CityMunicipalityController y se consulta antes que el manejador global.
 */
@RestControllerAdvice(assignableTypes = CityMunicipalityController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CityMunicipalityExceptionHandler {

    @ExceptionHandler(CityMunicipalityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(CityMunicipalityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(404, "Not Found", ex.getMessage()));
    }
}
