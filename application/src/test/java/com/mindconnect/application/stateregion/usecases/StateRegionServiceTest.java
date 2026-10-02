package com.mindconnect.application.stateregion.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.stateregion.dtos.StateRegionCommand;
import com.mindconnect.application.stateregion.exceptions.StateRegionNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.stateregion.model.aggregates.StateRegion;
import com.mindconnect.domain.stateregion.repositories.StateRegionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StateRegionServiceTest {

    private InMemoryStateRegionRepository repository;
    private StateRegionService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryStateRegionRepository();
        service = new StateRegionService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        StateRegion created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        StateRegionCommand invalid = new StateRegionCommand(
                null,
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(StateRegionNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        StateRegion created = service.create(commandA());
        StateRegionCommand nuevo = commandB();

        StateRegion updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameRegion(), updated.nameRegion());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(StateRegionNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        StateRegion created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(StateRegionNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private StateRegionCommand commandA() {
        return new StateRegionCommand(
                "valor-a",
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID());
    }

    private StateRegionCommand commandB() {
        return new StateRegionCommand(
                "valor-b",
                "valor-b",
                "valor-b",
                false,
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryStateRegionRepository implements StateRegionRepository {

        private final Map<UUID, StateRegion> store = new HashMap<>();

        @Override
        public StateRegion save(StateRegion stateRegion) {
            store.put(stateRegion.id(), stateRegion);
            return stateRegion;
        }

        @Override
        public Optional<StateRegion> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<StateRegion> findAll() {
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
