package com.mindconnect.application.citymunicipality.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.citymunicipality.dtos.CityMunicipalityCommand;
import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre city_municipalities.
 */
public interface CityMunicipalityUseCase {

    CityMunicipality create(CityMunicipalityCommand command);

    CityMunicipality getById(UUID id);

    List<CityMunicipality> getAll();

    CityMunicipality update(UUID id, CityMunicipalityCommand command);

    void delete(UUID id);
}
