package com.mindconnect.application.clinicalnote.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.clinicalnote.dtos.ClinicalNoteCommand;
import com.mindconnect.application.clinicalnote.exceptions.ClinicalNoteNotFoundException;
import com.mindconnect.domain.clinicalnote.model.aggregates.ClinicalNote;
import com.mindconnect.domain.clinicalnote.repositories.ClinicalNoteRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClinicalNoteServiceTest {

    private InMemoryClinicalNoteRepository repository;
    private ClinicalNoteService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryClinicalNoteRepository();
        service = new ClinicalNoteService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ClinicalNote created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ClinicalNoteCommand invalid = new ClinicalNoteCommand(
                null,
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0));

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalNoteNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ClinicalNote created = service.create(commandA());
        ClinicalNoteCommand nuevo = commandB();

        ClinicalNote updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.encounterId(), updated.encounterId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalNoteNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ClinicalNote created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ClinicalNoteNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ClinicalNoteCommand commandA() {
        return new ClinicalNoteCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                LocalDateTime.of(2026, 1, 15, 10, 0));
    }

    private ClinicalNoteCommand commandB() {
        return new ClinicalNoteCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                LocalDateTime.of(2026, 2, 20, 8, 30));
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryClinicalNoteRepository implements ClinicalNoteRepository {

        private final Map<UUID, ClinicalNote> store = new HashMap<>();

        @Override
        public ClinicalNote save(ClinicalNote clinicalNote) {
            store.put(clinicalNote.id(), clinicalNote);
            return clinicalNote;
        }

        @Override
        public Optional<ClinicalNote> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ClinicalNote> findAll() {
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
