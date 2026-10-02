package com.mindconnect.application.priority.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.priority.dtos.PriorityCommand;
import com.mindconnect.application.priority.exceptions.PriorityNotFoundException;
import com.mindconnect.domain.priority.model.aggregates.Priority;
import com.mindconnect.domain.priority.repositories.PriorityRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriorityServiceTest {

    private InMemoryPriorityRepository repository;
    private PriorityService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPriorityRepository();
        service = new PriorityService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Priority created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        PriorityCommand invalid = new PriorityCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PriorityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Priority created = service.create(commandA());
        PriorityCommand nuevo = commandB();

        Priority updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.namePriority(), updated.namePriority());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PriorityNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Priority created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(PriorityNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private PriorityCommand commandA() {
        return new PriorityCommand(
                "valor-a");
    }

    private PriorityCommand commandB() {
        return new PriorityCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryPriorityRepository implements PriorityRepository {

        private final Map<UUID, Priority> store = new HashMap<>();

        @Override
        public Priority save(Priority priority) {
            store.put(priority.id(), priority);
            return priority;
        }

        @Override
        public Optional<Priority> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Priority> findAll() {
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
