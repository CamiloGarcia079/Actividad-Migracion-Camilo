package com.mindconnect.application.clinicalrecordstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalrecordstatus.dtos.ClinicalRecordStatusCommand;
import com.mindconnect.application.clinicalrecordstatus.exceptions.ClinicalRecordStatusNotFoundException;
import com.mindconnect.domain.clinicalrecordstatus.model.aggregates.ClinicalRecordStatus;
import com.mindconnect.domain.clinicalrecordstatus.repositories.ClinicalRecordStatusRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClinicalRecordStatusServiceTest {

    private InMemoryClinicalRecordStatusRepository repository;
    private ClinicalRecordStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryClinicalRecordStatusRepository();
        service = new ClinicalRecordStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ClinicalRecordStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ClinicalRecordStatusCommand invalid = new ClinicalRecordStatusCommand(
                null,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ClinicalRecordStatus created = service.create(commandA());
        ClinicalRecordStatusCommand nuevo = commandB();

        ClinicalRecordStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ClinicalRecordStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalRecordStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ClinicalRecordStatusCommand commandA() {
        return new ClinicalRecordStatusCommand(
                "valor-a",
                "valor-a");
    }

    private ClinicalRecordStatusCommand commandB() {
        return new ClinicalRecordStatusCommand(
                "valor-b",
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryClinicalRecordStatusRepository implements ClinicalRecordStatusRepository {

        private final Map<UUID, ClinicalRecordStatus> store = new HashMap<>();

        @Override
        public ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus) {
            store.put(clinicalRecordStatus.id(), clinicalRecordStatus);
            return clinicalRecordStatus;
        }

        @Override
        public Optional<ClinicalRecordStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalRecordStatus> findAll() {
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
