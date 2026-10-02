package com.mindconnect.application.treatmentstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.treatmentstatus.dtos.TreatmentStatusCommand;
import com.mindconnect.application.treatmentstatus.exceptions.TreatmentStatusNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.treatmentstatus.model.aggregates.TreatmentStatus;
import com.mindconnect.domain.treatmentstatus.repositories.TreatmentStatusRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreatmentStatusServiceTest {

    private InMemoryTreatmentStatusRepository repository;
    private TreatmentStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTreatmentStatusRepository();
        service = new TreatmentStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        TreatmentStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        TreatmentStatusCommand invalid = new TreatmentStatusCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        TreatmentStatus created = service.create(commandA());
        TreatmentStatusCommand nuevo = commandB();

        TreatmentStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        TreatmentStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(TreatmentStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private TreatmentStatusCommand commandA() {
        return new TreatmentStatusCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private TreatmentStatusCommand commandB() {
        return new TreatmentStatusCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryTreatmentStatusRepository implements TreatmentStatusRepository {

        private final Map<UUID, TreatmentStatus> store = new HashMap<>();

        @Override
        public TreatmentStatus save(TreatmentStatus treatmentStatus) {
            store.put(treatmentStatus.id(), treatmentStatus);
            return treatmentStatus;
        }

        @Override
        public Optional<TreatmentStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<TreatmentStatus> findAll() {
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
