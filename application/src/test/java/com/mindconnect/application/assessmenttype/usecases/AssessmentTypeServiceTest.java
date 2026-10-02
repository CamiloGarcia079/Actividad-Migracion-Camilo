package com.mindconnect.application.assessmenttype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.assessmenttype.dtos.AssessmentTypeCommand;
import com.mindconnect.application.assessmenttype.exceptions.AssessmentTypeNotFoundException;
import com.mindconnect.domain.assessmenttype.model.aggregates.AssessmentType;
import com.mindconnect.domain.assessmenttype.repositories.AssessmentTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssessmentTypeServiceTest {

    private InMemoryAssessmentTypeRepository repository;
    private AssessmentTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryAssessmentTypeRepository();
        service = new AssessmentTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        AssessmentType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        AssessmentTypeCommand invalid = new AssessmentTypeCommand(
                null,
                "valor-a",
                true,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(AssessmentTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        AssessmentType created = service.create(commandA());
        AssessmentTypeCommand nuevo = commandB();

        AssessmentType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(AssessmentTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        AssessmentType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(AssessmentTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private AssessmentTypeCommand commandA() {
        return new AssessmentTypeCommand(
                "valor-a",
                "valor-a",
                true,
                "valor-a");
    }

    private AssessmentTypeCommand commandB() {
        return new AssessmentTypeCommand(
                "valor-b",
                "valor-b",
                false,
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryAssessmentTypeRepository implements AssessmentTypeRepository {

        private final Map<UUID, AssessmentType> store = new HashMap<>();

        @Override
        public AssessmentType save(AssessmentType assessmentType) {
            store.put(assessmentType.id(), assessmentType);
            return assessmentType;
        }

        @Override
        public Optional<AssessmentType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AssessmentType> findAll() {
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
