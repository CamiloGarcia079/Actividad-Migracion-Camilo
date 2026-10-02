package com.mindconnect.application.country.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.country.dtos.CountryCommand;
import com.mindconnect.application.country.exceptions.CountryNotFoundException;
import com.mindconnect.application.country.ports.in.CountryUseCase;
import com.mindconnect.domain.country.model.aggregates.Country;
import com.mindconnect.domain.country.repositories.CountryRepository;

/**
 * Implementación de los casos de uso de countries.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class CountryService implements CountryUseCase {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country create(CountryCommand command) {
        LocalDateTime now = LocalDateTime.now();
        Country country = new Country(
                UUID.randomUUID(),
                command.nameCountry(),
                command.codeCountry(),
                command.description(),
                command.isActive(),
                command.telephonePrefix(),
                now,
                now);
        return repository.save(country);
    }

    @Override
    public Country getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public List<Country> getAll() {
        return repository.findAll();
    }

    @Override
    public Country update(UUID id, CountryCommand command) {
        Country existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        Country updated = new Country(
                existing.id(),
                command.nameCountry(),
                command.codeCountry(),
                command.description(),
                command.isActive(),
                command.telephonePrefix(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new CountryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
