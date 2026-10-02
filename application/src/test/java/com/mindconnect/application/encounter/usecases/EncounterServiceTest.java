package com.mindconnect.application.encounter.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encounter.dtos.EncounterCommand;
import com.mindconnect.application.encounter.exceptions.EncounterNotFoundException;
import com.mindconnect.domain.encounter.model.aggregates.Encounter;
import com.mindconnect.domain.encounter.repositories.EncounterRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterServiceTest {

    private InMemoryEncounterRepository repository;
    private EncounterService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEncounterRepository();
        service = new EncounterService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Encounter created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EncounterCommand invalid = new EncounterCommand(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Encounter created = service.create(commandA());
        EncounterCommand nuevo = commandB();

        Encounter updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.clinicalRecordId(), updated.clinicalRecordId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Encounter created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EncounterCommand commandA() {
        return new EncounterCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private EncounterCommand commandB() {
        return new EncounterCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                "valor-b",
                "valor-b",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEncounterRepository implements EncounterRepository {

        private final Map<UUID, Encounter> store = new HashMap<>();

        @Override
        public Encounter save(Encounter encounter) {
            store.put(encounter.id(), encounter);
            return encounter;
        }

        @Override
        public Optional<Encounter> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Encounter> findAll() {
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
