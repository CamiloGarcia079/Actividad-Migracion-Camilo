package com.mindconnect.application.riskassessment.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.riskassessment.dtos.RiskAssessmentCommand;
import com.mindconnect.application.riskassessment.exceptions.RiskAssessmentNotFoundException;
import com.mindconnect.domain.riskassessment.model.aggregates.RiskAssessment;
import com.mindconnect.domain.riskassessment.repositories.RiskAssessmentRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiskAssessmentServiceTest {

    private InMemoryRiskAssessmentRepository repository;
    private RiskAssessmentService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRiskAssessmentRepository();
        service = new RiskAssessmentService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        RiskAssessment created = service.create(commandA());

        assertNotNull(created.id());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        RiskAssessmentCommand invalid = new RiskAssessmentCommand(
                null,
                UUID.randomUUID(),
                true,
                true,
                true,
                true,
                true,
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskAssessmentNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        RiskAssessment created = service.create(commandA());
        RiskAssessmentCommand nuevo = commandB();

        RiskAssessment updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(nuevo.encounterId(), updated.encounterId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskAssessmentNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        RiskAssessment created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(RiskAssessmentNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private RiskAssessmentCommand commandA() {
        return new RiskAssessmentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                true,
                true,
                true,
                true,
                true,
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID());
    }

    private RiskAssessmentCommand commandB() {
        return new RiskAssessmentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                false,
                false,
                false,
                false,
                false,
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                LocalDateTime.of(2026, 2, 20, 8, 30),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryRiskAssessmentRepository implements RiskAssessmentRepository {

        private final Map<UUID, RiskAssessment> store = new HashMap<>();

        @Override
        public RiskAssessment save(RiskAssessment riskAssessment) {
            store.put(riskAssessment.id(), riskAssessment);
            return riskAssessment;
        }

        @Override
        public Optional<RiskAssessment> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RiskAssessment> findAll() {
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
