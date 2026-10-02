package com.mindconnect.application.treatmentgoalstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentgoalstatus.dtos.TreatmentGoalStatusCommand;
import com.mindconnect.application.treatmentgoalstatus.exceptions.TreatmentGoalStatusNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.treatmentgoalstatus.model.aggregates.TreatmentGoalStatus;
import com.mindconnect.domain.treatmentgoalstatus.repositories.TreatmentGoalStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentGoalStatusServiceTest {

    private InMemoryTreatmentGoalStatusRepository repository;
    private TreatmentGoalStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTreatmentGoalStatusRepository();
        service = new TreatmentGoalStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        TreatmentGoalStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        TreatmentGoalStatusCommand invalid = new TreatmentGoalStatusCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        TreatmentGoalStatus created = service.create(commandA());
        TreatmentGoalStatusCommand nuevo = commandB();

        TreatmentGoalStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        TreatmentGoalStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentGoalStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private TreatmentGoalStatusCommand commandA() {
        return new TreatmentGoalStatusCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private TreatmentGoalStatusCommand commandB() {
        return new TreatmentGoalStatusCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryTreatmentGoalStatusRepository implements TreatmentGoalStatusRepository {

        private final Map<UUID, TreatmentGoalStatus> store = new HashMap<>();

        @Override
        public TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus) {
            store.put(treatmentGoalStatus.id(), treatmentGoalStatus);
            return treatmentGoalStatus;
        }

        @Override
        public Optional<TreatmentGoalStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentGoalStatus> findAll() {
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
