package com.mindconnect.application.encounterstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounterstatus.dtos.EncounterStatusCommand;
import com.mindconnect.application.encounterstatus.exceptions.EncounterStatusNotFoundException;
import com.mindconnect.domain.encounterstatus.model.aggregates.EncounterStatus;
import com.mindconnect.domain.encounterstatus.repositories.EncounterStatusRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterStatusServiceTest {

    private InMemoryEncounterStatusRepository repository;
    private EncounterStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEncounterStatusRepository();
        service = new EncounterStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        EncounterStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EncounterStatusCommand invalid = new EncounterStatusCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        EncounterStatus created = service.create(commandA());
        EncounterStatusCommand nuevo = commandB();

        EncounterStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        EncounterStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EncounterStatusCommand commandA() {
        return new EncounterStatusCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private EncounterStatusCommand commandB() {
        return new EncounterStatusCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEncounterStatusRepository implements EncounterStatusRepository {

        private final Map<UUID, EncounterStatus> store = new HashMap<>();

        @Override
        public EncounterStatus save(EncounterStatus encounterStatus) {
            store.put(encounterStatus.id(), encounterStatus);
            return encounterStatus;
        }

        @Override
        public Optional<EncounterStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterStatus> findAll() {
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
