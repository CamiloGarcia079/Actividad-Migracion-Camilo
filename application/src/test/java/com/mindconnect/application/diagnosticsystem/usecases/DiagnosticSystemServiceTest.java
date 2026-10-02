package com.mindconnect.application.diagnosticsystem.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.diagnosticsystem.dtos.DiagnosticSystemCommand;
import com.mindconnect.application.diagnosticsystem.exceptions.DiagnosticSystemNotFoundException;
import com.mindconnect.domain.diagnosticsystem.model.aggregates.DiagnosticSystem;
import com.mindconnect.domain.diagnosticsystem.repositories.DiagnosticSystemRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiagnosticSystemServiceTest {

    private InMemoryDiagnosticSystemRepository repository;
    private DiagnosticSystemService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryDiagnosticSystemRepository();
        service = new DiagnosticSystemService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        DiagnosticSystem created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        DiagnosticSystemCommand invalid = new DiagnosticSystemCommand(
                null,
                "valor-a",
                true,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(DiagnosticSystemNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        DiagnosticSystem created = service.create(commandA());
        DiagnosticSystemCommand nuevo = commandB();

        DiagnosticSystem updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(DiagnosticSystemNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        DiagnosticSystem created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(DiagnosticSystemNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private DiagnosticSystemCommand commandA() {
        return new DiagnosticSystemCommand(
                "valor-a",
                "valor-a",
                true,
                "valor-a");
    }

    private DiagnosticSystemCommand commandB() {
        return new DiagnosticSystemCommand(
                "valor-b",
                "valor-b",
                false,
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryDiagnosticSystemRepository implements DiagnosticSystemRepository {

        private final Map<UUID, DiagnosticSystem> store = new HashMap<>();

        @Override
        public DiagnosticSystem save(DiagnosticSystem diagnosticSystem) {
            store.put(diagnosticSystem.id(), diagnosticSystem);
            return diagnosticSystem;
        }

        @Override
        public Optional<DiagnosticSystem> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<DiagnosticSystem> findAll() {
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
