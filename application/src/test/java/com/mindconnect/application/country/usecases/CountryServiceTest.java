package com.mindconnect.application.country.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.country.dtos.CountryCommand;
import com.mindconnect.application.country.exceptions.CountryNotFoundException;
import com.mindconnect.domain.country.model.aggregates.Country;
import com.mindconnect.domain.country.repositories.CountryRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CountryServiceTest {

    private InMemoryCountryRepository repository;
    private CountryService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCountryRepository();
        service = new CountryService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Country created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        CountryCommand invalid = new CountryCommand(
                null,
                "valor-a",
                "valor-a",
                true,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(CountryNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Country created = service.create(commandA());
        CountryCommand nuevo = commandB();

        Country updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameCountry(), updated.nameCountry());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(CountryNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Country created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(CountryNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private CountryCommand commandA() {
        return new CountryCommand(
                "valor-a",
                "valor-a",
                "valor-a",
                true,
                "valor-a");
    }

    private CountryCommand commandB() {
        return new CountryCommand(
                "valor-b",
                "valor-b",
                "valor-b",
                false,
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryCountryRepository implements CountryRepository {

        private final Map<UUID, Country> store = new HashMap<>();

        @Override
        public Country save(Country country) {
            store.put(country.id(), country);
            return country;
        }

        @Override
        public Optional<Country> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Country> findAll() {
            return new ArrayList<>(store.values());
        }

        @Override
        public boolean existsById(UUID id) {
            return store.containsKey(id);
        }

        @Override
        public void deleteById(UUID id) {
            store.remove(id);
        }
    }
}
