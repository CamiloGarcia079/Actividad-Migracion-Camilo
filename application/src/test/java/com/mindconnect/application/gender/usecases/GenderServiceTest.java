package com.mindconnect.application.gender.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.gender.dtos.GenderCommand;
import com.mindconnect.application.gender.exceptions.GenderNotFoundException;
import com.mindconnect.domain.gender.model.aggregates.Gender;
import com.mindconnect.domain.gender.repositories.GenderRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenderServiceTest {

    private InMemoryGenderRepository repository;
    private GenderService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryGenderRepository();
        service = new GenderService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Gender created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        GenderCommand invalid = new GenderCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(GenderNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Gender created = service.create(commandA());
        GenderCommand nuevo = commandB();

        Gender updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.description(), updated.description());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(GenderNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Gender created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(GenderNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private GenderCommand commandA() {
        return new GenderCommand(
                "valor-a");
    }

    private GenderCommand commandB() {
        return new GenderCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryGenderRepository implements GenderRepository {

        private final Map<UUID, Gender> store = new HashMap<>();

        @Override
        public Gender save(Gender gender) {
            store.put(gender.id(), gender);
            return gender;
        }

        @Override
        public Optional<Gender> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Gender> findAll() {
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
