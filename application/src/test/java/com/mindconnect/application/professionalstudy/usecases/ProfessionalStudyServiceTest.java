package com.mindconnect.application.professionalstudy.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionalstudy.dtos.ProfessionalStudyCommand;
import com.mindconnect.application.professionalstudy.exceptions.ProfessionalStudyNotFoundException;
import com.mindconnect.domain.professionalstudy.model.aggregates.ProfessionalStudy;
import com.mindconnect.domain.professionalstudy.repositories.ProfessionalStudyRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfessionalStudyServiceTest {

    private InMemoryProfessionalStudyRepository repository;
    private ProfessionalStudyService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProfessionalStudyRepository();
        service = new ProfessionalStudyService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ProfessionalStudy created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ProfessionalStudyCommand invalid = new ProfessionalStudyCommand(
                null,
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                true,
                "valor-a",
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalStudyNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ProfessionalStudy created = service.create(commandA());
        ProfessionalStudyCommand nuevo = commandB();

        ProfessionalStudy updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.studyId(), updated.studyId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalStudyNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ProfessionalStudy created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalStudyNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ProfessionalStudyCommand commandA() {
        return new ProfessionalStudyCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                true,
                "valor-a",
                UUID.randomUUID());
    }

    private ProfessionalStudyCommand commandB() {
        return new ProfessionalStudyCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                false,
                "valor-b",
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryProfessionalStudyRepository implements ProfessionalStudyRepository {

        private final Map<UUID, ProfessionalStudy> store = new HashMap<>();

        @Override
        public ProfessionalStudy save(ProfessionalStudy professionalStudy) {
            store.put(professionalStudy.id(), professionalStudy);
            return professionalStudy;
        }

        @Override
        public Optional<ProfessionalStudy> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalStudy> findAll() {
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
