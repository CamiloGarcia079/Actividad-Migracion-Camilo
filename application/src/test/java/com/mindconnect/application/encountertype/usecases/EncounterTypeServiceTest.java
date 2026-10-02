package com.mindconnect.application.encountertype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.encountertype.dtos.EncounterTypeCommand;
import com.mindconnect.application.encountertype.exceptions.EncounterTypeNotFoundException;
import com.mindconnect.domain.encountertype.model.aggregates.EncounterType;
import com.mindconnect.domain.encountertype.repositories.EncounterTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterTypeServiceTest {

    private InMemoryEncounterTypeRepository repository;
    private EncounterTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEncounterTypeRepository();
        service = new EncounterTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        EncounterType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EncounterTypeCommand invalid = new EncounterTypeCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        EncounterType created = service.create(commandA());
        EncounterTypeCommand nuevo = commandB();

        EncounterType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        EncounterType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EncounterTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EncounterTypeCommand commandA() {
        return new EncounterTypeCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private EncounterTypeCommand commandB() {
        return new EncounterTypeCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEncounterTypeRepository implements EncounterTypeRepository {

        private final Map<UUID, EncounterType> store = new HashMap<>();

        @Override
        public EncounterType save(EncounterType encounterType) {
            store.put(encounterType.id(), encounterType);
            return encounterType;
        }

        @Override
        public Optional<EncounterType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EncounterType> findAll() {
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
