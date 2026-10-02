package com.mindconnect.application.citymunicipality.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.citymunicipality.dtos.CityMunicipalityCommand;
import com.mindconnect.application.citymunicipality.exceptions.CityMunicipalityNotFoundException;
import com.mindconnect.domain.citymunicipality.model.aggregates.CityMunicipality;
import com.mindconnect.domain.citymunicipality.repositories.CityMunicipalityRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CityMunicipalityServiceTest {

    private InMemoryCityMunicipalityRepository repository;
    private CityMunicipalityService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCityMunicipalityRepository();
        service = new CityMunicipalityService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        CityMunicipality created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        CityMunicipalityCommand invalid = new CityMunicipalityCommand(
                null,
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(CityMunicipalityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        CityMunicipality created = service.create(commandA());
        CityMunicipalityCommand nuevo = commandB();

        CityMunicipality updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameCity(), updated.nameCity());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(CityMunicipalityNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        CityMunicipality created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(CityMunicipalityNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private CityMunicipalityCommand commandA() {
        return new CityMunicipalityCommand(
                "valor-a",
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID());
    }

    private CityMunicipalityCommand commandB() {
        return new CityMunicipalityCommand(
                "valor-b",
                "valor-b",
                "valor-b",
                false,
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryCityMunicipalityRepository implements CityMunicipalityRepository {

        private final Map<UUID, CityMunicipality> store = new HashMap<>();

        @Override
        public CityMunicipality save(CityMunicipality cityMunicipality) {
            store.put(cityMunicipality.id(), cityMunicipality);
            return cityMunicipality;
        }

        @Override
        public Optional<CityMunicipality> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<CityMunicipality> findAll() {
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
