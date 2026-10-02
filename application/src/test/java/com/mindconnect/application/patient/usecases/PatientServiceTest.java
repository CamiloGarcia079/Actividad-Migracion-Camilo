package com.mindconnect.application.patient.usecases;

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

import com.mindconnect.application.patient.dtos.PatientCommand;
import com.mindconnect.application.patient.exceptions.PatientNotFoundException;
import com.mindconnect.domain.patient.model.aggregates.Patient;
import com.mindconnect.domain.patient.repositories.PatientRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientServiceTest {

    private InMemoryPatientRepository repository;
    private PatientService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPatientRepository();
        service = new PatientService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Patient created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        PatientCommand invalid = new PatientCommand(
                null,
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDate.of(2026, 1, 15),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Patient created = service.create(commandA());
        PatientCommand nuevo = commandB();

        Patient updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.documentTypeId(), updated.documentTypeId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Patient created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private PatientCommand commandA() {
        return new PatientCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDate.of(2026, 1, 15),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                true,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private PatientCommand commandB() {
        return new PatientCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                LocalDate.of(2026, 2, 20),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b",
                false,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryPatientRepository implements PatientRepository {

        private final Map<UUID, Patient> store = new HashMap<>();

        @Override
        public Patient save(Patient patient) {
            store.put(patient.id(), patient);
            return patient;
        }

        @Override
        public Optional<Patient> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Patient> findAll() {
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
