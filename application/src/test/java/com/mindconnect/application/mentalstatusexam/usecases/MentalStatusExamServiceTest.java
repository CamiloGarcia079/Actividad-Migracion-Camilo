package com.mindconnect.application.mentalstatusexam.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.mentalstatusexam.dtos.MentalStatusExamCommand;
import com.mindconnect.application.mentalstatusexam.exceptions.MentalStatusExamNotFoundException;
import com.mindconnect.domain.mentalstatusexam.model.aggregates.MentalStatusExam;
import com.mindconnect.domain.mentalstatusexam.repositories.MentalStatusExamRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MentalStatusExamServiceTest {

    private InMemoryMentalStatusExamRepository repository;
    private MentalStatusExamService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMentalStatusExamRepository();
        service = new MentalStatusExamService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        MentalStatusExam created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        MentalStatusExamCommand invalid = new MentalStatusExamCommand(
                null,
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MentalStatusExamNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        MentalStatusExam created = service.create(commandA());
        MentalStatusExamCommand nuevo = commandB();

        MentalStatusExam updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.encounterId(), updated.encounterId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MentalStatusExamNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        MentalStatusExam created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(MentalStatusExamNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private MentalStatusExamCommand commandA() {
        return new MentalStatusExamCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                "valor-a",
                UUID.randomUUID());
    }

    private MentalStatusExamCommand commandB() {
        return new MentalStatusExamCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                "valor-b",
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryMentalStatusExamRepository implements MentalStatusExamRepository {

        private final Map<UUID, MentalStatusExam> store = new HashMap<>();

        @Override
        public MentalStatusExam save(MentalStatusExam mentalStatusExam) {
            store.put(mentalStatusExam.id(), mentalStatusExam);
            return mentalStatusExam;
        }

        @Override
        public Optional<MentalStatusExam> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MentalStatusExam> findAll() {
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
