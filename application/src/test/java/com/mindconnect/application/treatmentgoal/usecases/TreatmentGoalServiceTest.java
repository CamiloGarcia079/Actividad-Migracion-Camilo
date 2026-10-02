package com.mindconnect.application.treatmentgoal.usecases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoal.dtos.TreatmentGoalCommand;
import com.mindconnect.application.treatmentgoal.exceptions.TreatmentGoalNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.treatmentgoal.model.aggregates.TreatmentGoal;
import com.mindconnect.domain.treatmentgoal.repositories.TreatmentGoalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentGoalServiceTest {

    private InMemoryTreatmentGoalRepository repository;
    private TreatmentGoalService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTreatmentGoalRepository();
        service = new TreatmentGoalService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        TreatmentGoal created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        TreatmentGoalCommand invalid = new TreatmentGoalCommand(
                null,
                "valor-a",
                LocalDate.of(2026, 1, 15),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        TreatmentGoal created = service.create(commandA());
        TreatmentGoalCommand nuevo = commandB();

        TreatmentGoal updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.treatmentPlanId(), updated.treatmentPlanId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        TreatmentGoal created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private TreatmentGoalCommand commandA() {
        return new TreatmentGoalCommand(
                UUID.randomUUID(),
                "valor-a",
                LocalDate.of(2026, 1, 15),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                UUID.randomUUID());
    }

    private TreatmentGoalCommand commandB() {
        return new TreatmentGoalCommand(
                UUID.randomUUID(),
                "valor-b",
                LocalDate.of(2026, 2, 20),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                "valor-b",
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryTreatmentGoalRepository implements TreatmentGoalRepository {

        private final Map<UUID, TreatmentGoal> store = new HashMap<>();

        @Override
        public TreatmentGoal save(TreatmentGoal treatmentGoal) {
            store.put(treatmentGoal.id(), treatmentGoal);
            return treatmentGoal;
        }

        @Override
        public Optional<TreatmentGoal> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoal> findAll() {
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
