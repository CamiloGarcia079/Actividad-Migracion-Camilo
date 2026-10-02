package com.mindconnect.application.professional.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professional.dtos.ProfessionalCommand;
import com.mindconnect.application.professional.exceptions.ProfessionalNotFoundException;
import com.mindconnect.domain.professional.model.aggregates.Professional;
import com.mindconnect.domain.professional.repositories.ProfessionalRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfessionalServiceTest {

    private InMemoryProfessionalRepository repository;
    private ProfessionalService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProfessionalRepository();
        service = new ProfessionalService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Professional created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ProfessionalCommand invalid = new ProfessionalCommand(
                null,
                "valor-a",
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                "valor-a",
                true,
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Professional created = service.create(commandA());
        ProfessionalCommand nuevo = commandB();

        Professional updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.documentTypeId(), updated.documentTypeId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Professional created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ProfessionalCommand commandA() {
        return new ProfessionalCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                "valor-a",
                true,
                UUID.randomUUID());
    }

    private ProfessionalCommand commandB() {
        return new ProfessionalCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b",
                UUID.randomUUID(),
                "valor-b",
                false,
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryProfessionalRepository implements ProfessionalRepository {

        private final Map<UUID, Professional> store = new HashMap<>();

        @Override
        public Professional save(Professional professional) {
            store.put(professional.id(), professional);
            return professional;
        }

        @Override
        public Optional<Professional> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Professional> findAll() {
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
