package com.mindconnect.application.medicationroute.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.medicationroute.dtos.MedicationRouteCommand;
import com.mindconnect.application.medicationroute.exceptions.MedicationRouteNotFoundException;
import com.mindconnect.domain.medicationroute.model.aggregates.MedicationRoute;
import com.mindconnect.domain.medicationroute.repositories.MedicationRouteRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicationRouteServiceTest {

    private InMemoryMedicationRouteRepository repository;
    private MedicationRouteService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMedicationRouteRepository();
        service = new MedicationRouteService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        MedicationRoute created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        MedicationRouteCommand invalid = new MedicationRouteCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MedicationRouteNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        MedicationRoute created = service.create(commandA());
        MedicationRouteCommand nuevo = commandB();

        MedicationRoute updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MedicationRouteNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        MedicationRoute created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(MedicationRouteNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private MedicationRouteCommand commandA() {
        return new MedicationRouteCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private MedicationRouteCommand commandB() {
        return new MedicationRouteCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryMedicationRouteRepository implements MedicationRouteRepository {

        private final Map<UUID, MedicationRoute> store = new HashMap<>();

        @Override
        public MedicationRoute save(MedicationRoute medicationRoute) {
            store.put(medicationRoute.id(), medicationRoute);
            return medicationRoute;
        }

        @Override
        public Optional<MedicationRoute> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MedicationRoute> findAll() {
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
