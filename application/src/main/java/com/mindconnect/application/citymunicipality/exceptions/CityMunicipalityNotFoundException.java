package com.mindconnect.application.citymunicipality.exceptions;

import java.util.UUID;

import com.mindconnect.application.shared.exceptions.ApplicationException;

/**
 * No existe un registro de city_municipalities con el id pedido.
 */
public class CityMunicipalityNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public CityMunicipalityNotFoundException(UUID id) {
        super("No se encontró un registro de city_municipalities con id " + id);
    }
}
