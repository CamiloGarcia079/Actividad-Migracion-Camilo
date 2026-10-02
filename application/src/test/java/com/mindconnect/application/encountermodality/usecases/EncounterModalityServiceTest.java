package com.mindconnect.application.encountermodality.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountermodality.dtos.EncounterModalityCommand;
import com.mindconnect.application.encountermodality.exceptions.EncounterModalityNotFoundException;
import com.mindconnect.domain.encountermodality.model.aggregates.EncounterModality;
import com.mindconnect.domain.encountermodality.repositories.EncounterModalityRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterModalityServiceTest {

    private InMemoryEncounterModalityRepository repository;
    private EncounterModalityService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEncounterModalityRepository();
        service = new EncounterModalityService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        EncounterModality created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EncounterModalityCommand invalid = new EncounterModalityCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterModalityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        EncounterModality created = service.create(commandA());
        EncounterModalityCommand nuevo = commandB();

        EncounterModality updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterModalityNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        EncounterModality created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterModalityNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EncounterModalityCommand commandA() {
        return new EncounterModalityCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private EncounterModalityCommand commandB() {
        return new EncounterModalityCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEncounterModalityRepository implements EncounterModalityRepository {

        private final Map<UUID, EncounterModality> store = new HashMap<>();

        @Override
        public EncounterModality save(EncounterModality encounterModality) {
            store.put(encounterModality.id(), encounterModality);
            return encounterModality;
        }

        @Override
        public Optional<EncounterModality> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterModality> findAll() {
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
