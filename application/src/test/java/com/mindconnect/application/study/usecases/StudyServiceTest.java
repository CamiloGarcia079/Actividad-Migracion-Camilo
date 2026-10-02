package com.mindconnect.application.study.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.study.dtos.StudyCommand;
import com.mindconnect.application.study.exceptions.StudyNotFoundException;
import com.mindconnect.domain.shared.exceptions.DomainException;
import com.mindconnect.domain.study.model.aggregates.Study;
import com.mindconnect.domain.study.repositories.StudyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudyServiceTest {

    private InMemoryStudyRepository repository;
    private StudyService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryStudyRepository();
        service = new StudyService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Study created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        StudyCommand invalid = new StudyCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(StudyNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Study created = service.create(commandA());
        StudyCommand nuevo = commandB();

        Study updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.name(), updated.name());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(StudyNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Study created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(StudyNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private StudyCommand commandA() {
        return new StudyCommand(
                "valor-a");
    }

    private StudyCommand commandB() {
        return new StudyCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryStudyRepository implements StudyRepository {

        private final Map<UUID, Study> store = new HashMap<>();

        @Override
        public Study save(Study study) {
            store.put(study.id(), study);
            return study;
        }

        @Override
        public Optional<Study> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Study> findAll() {
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
