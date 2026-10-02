package com.mindconnect.application.citymunicipality.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.citymunicipality.dtos.CityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.exceptions.CityMunicipalityNotFoundException;
import com.mindconnect.application.citymunicipality.ports.in.CityMunicipalityUseCase;
import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;
import com.mindconnect.domain.citymunicipality.repositories.CityMunicipalityRepository;

/**
 * Implementación de los casos de uso de city_municipalities.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class CityMunicipalityService implements CityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;

    public CityMunicipalityService(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    @Override
    public CityMunicipality create(CityMunicipalityCommand command) {
        LocalDateTime now = LocalDateTime.now();
        CityMunicipality cityMunicipality = new CityMunicipality(
                UUID.randomUUID(),
                command.nameCity(),
                command.codeCity(),
                command.description(),
                command.isActive(),
                command.regionId(),
                now,
                now);
        return repository.save(cityMunicipality);
    }

    @Override
    public CityMunicipality getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CityMunicipalityNotFoundException(id));
    }

    @Override
    public List<CityMunicipality> getAll() {
        return repository.findAll();
    }

    @Override
    public CityMunicipality update(UUID id, CityMunicipalityCommand command) {
        CityMunicipality existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        CityMunicipality updated = new CityMunicipality(
                existing.id(),
                command.nameCity(),
                command.codeCity(),
                command.description(),
                command.isActive(),
                command.regionId(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new CityMunicipalityNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
