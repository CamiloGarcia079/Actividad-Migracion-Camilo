package com.mindconnect.application.escalationstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.escalationstatus.dtos.EscalationStatusCommand;
import com.mindconnect.application.escalationstatus.exceptions.EscalationStatusNotFoundException;
import com.mindconnect.domain.escalationstatus.model.aggregates.EscalationStatus;
import com.mindconnect.domain.escalationstatus.repositories.EscalationStatusRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EscalationStatusServiceTest {

    private InMemoryEscalationStatusRepository repository;
    private EscalationStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEscalationStatusRepository();
        service = new EscalationStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        EscalationStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EscalationStatusCommand invalid = new EscalationStatusCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EscalationStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        EscalationStatus created = service.create(commandA());
        EscalationStatusCommand nuevo = commandB();

        EscalationStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameStatus(), updated.nameStatus());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EscalationStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        EscalationStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EscalationStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EscalationStatusCommand commandA() {
        return new EscalationStatusCommand(
                "valor-a");
    }

    private EscalationStatusCommand commandB() {
        return new EscalationStatusCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEscalationStatusRepository implements EscalationStatusRepository {

        private final Map<UUID, EscalationStatus> store = new HashMap<>();

        @Override
        public EscalationStatus save(EscalationStatus escalationStatus) {
            store.put(escalationStatus.id(), escalationStatus);
            return escalationStatus;
        }

        @Override
        public Optional<EscalationStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EscalationStatus> findAll() {
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
