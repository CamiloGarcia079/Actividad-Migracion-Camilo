package com.mindconnect.application.risklevel.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.risklevel.dtos.RiskLevelCommand;
import com.mindconnect.application.risklevel.exceptions.RiskLevelNotFoundException;
import com.mindconnect.domain.risklevel.model.aggregates.RiskLevel;
import com.mindconnect.domain.risklevel.repositories.RiskLevelRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiskLevelServiceTest {

    private InMemoryRiskLevelRepository repository;
    private RiskLevelService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRiskLevelRepository();
        service = new RiskLevelService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        RiskLevel created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        RiskLevelCommand invalid = new RiskLevelCommand(
                null,
                "valor-a",
                true,
                1);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskLevelNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        RiskLevel created = service.create(commandA());
        RiskLevelCommand nuevo = commandB();

        RiskLevel updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskLevelNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        RiskLevel created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskLevelNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private RiskLevelCommand commandA() {
        return new RiskLevelCommand(
                "valor-a",
                "valor-a",
                true,
                1);
    }

    private RiskLevelCommand commandB() {
        return new RiskLevelCommand(
                "valor-b",
                "valor-b",
                false,
                2);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryRiskLevelRepository implements RiskLevelRepository {

        private final Map<UUID, RiskLevel> store = new HashMap<>();

        @Override
        public RiskLevel save(RiskLevel riskLevel) {
            store.put(riskLevel.id(), riskLevel);
            return riskLevel;
        }

        @Override
        public Optional<RiskLevel> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskLevel> findAll() {
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
