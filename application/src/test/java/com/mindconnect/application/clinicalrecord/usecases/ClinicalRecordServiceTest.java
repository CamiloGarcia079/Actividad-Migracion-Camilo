package com.mindconnect.application.clinicalrecord.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecord.dtos.ClinicalRecordCommand;
import com.mindconnect.application.clinicalrecord.exceptions.ClinicalRecordNotFoundException;
import com.mindconnect.domain.clinicalrecord.model.aggregates.ClinicalRecord;
import com.mindconnect.domain.clinicalrecord.repositories.ClinicalRecordRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClinicalRecordServiceTest {

    private InMemoryClinicalRecordRepository repository;
    private ClinicalRecordService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryClinicalRecordRepository();
        service = new ClinicalRecordService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ClinicalRecord created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ClinicalRecordCommand invalid = new ClinicalRecordCommand(
                null,
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ClinicalRecord created = service.create(commandA());
        ClinicalRecordCommand nuevo = commandB();

        ClinicalRecord updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.patientId(), updated.patientId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ClinicalRecord created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ClinicalRecordCommand commandA() {
        return new ClinicalRecordCommand(
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private ClinicalRecordCommand commandB() {
        return new ClinicalRecordCommand(
                UUID.randomUUID(),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                "valor-b",
                LocalDateTime.of(2026, 2, 20, 8, 30),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryClinicalRecordRepository implements ClinicalRecordRepository {

        private final Map<UUID, ClinicalRecord> store = new HashMap<>();

        @Override
        public ClinicalRecord save(ClinicalRecord clinicalRecord) {
            store.put(clinicalRecord.id(), clinicalRecord);
            return clinicalRecord;
        }

        @Override
        public Optional<ClinicalRecord> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecord> findAll() {
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
