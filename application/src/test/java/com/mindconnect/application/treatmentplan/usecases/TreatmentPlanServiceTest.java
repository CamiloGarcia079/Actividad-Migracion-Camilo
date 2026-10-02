package com.mindconnect.application.treatmentplan.usecases;

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

import com.mindconnect.application.treatmentplan.dtos.TreatmentPlanCommand;
import com.mindconnect.application.treatmentplan.exceptions.TreatmentPlanNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.treatmentplan.model.aggregates.TreatmentPlan;
import com.mindconnect.domain.treatmentplan.repositories.TreatmentPlanRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentPlanServiceTest {

    private InMemoryTreatmentPlanRepository repository;
    private TreatmentPlanService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTreatmentPlanRepository();
        service = new TreatmentPlanService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        TreatmentPlan created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        TreatmentPlanCommand invalid = new TreatmentPlanCommand(
                null,
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 15),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentPlanNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        TreatmentPlan created = service.create(commandA());
        TreatmentPlanCommand nuevo = commandB();

        TreatmentPlan updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.encounterId(), updated.encounterId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentPlanNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        TreatmentPlan created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentPlanNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private TreatmentPlanCommand commandA() {
        return new TreatmentPlanCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                LocalDate.of(2026, 1, 15),
                LocalDate.of(2026, 1, 15),
                UUID.randomUUID());
    }

    private TreatmentPlanCommand commandB() {
        return new TreatmentPlanCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                LocalDate.of(2026, 2, 20),
                LocalDate.of(2026, 2, 20),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryTreatmentPlanRepository implements TreatmentPlanRepository {

        private final Map<UUID, TreatmentPlan> store = new HashMap<>();

        @Override
        public TreatmentPlan save(TreatmentPlan treatmentPlan) {
            store.put(treatmentPlan.id(), treatmentPlan);
            return treatmentPlan;
        }

        @Override
        public Optional<TreatmentPlan> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentPlan> findAll() {
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
