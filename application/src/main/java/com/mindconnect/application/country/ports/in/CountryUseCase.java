package com.mindconnect.application.country.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.country.dtos.CountryCommand;
import com.mindconnect.domain.country.model.aggregates.Country;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre countries.
 */
public interface CountryUseCase {

    Country create(CountryCommand command);

    Country getById(UUID id);

    List<Country> getAll();

    Country update(UUID id, CountryCommand command);

    void delete(UUID id);
}
