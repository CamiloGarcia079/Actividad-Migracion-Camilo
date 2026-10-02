package com.mindconnect.application.sendertype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.sendertype.dtos.SenderTypeCommand;
import com.mindconnect.application.sendertype.exceptions.SenderTypeNotFoundException;
import com.mindconnect.domain.sendertype.model.aggregates.SenderType;
import com.mindconnect.domain.sendertype.repositories.SenderTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SenderTypeServiceTest {

    private InMemorySenderTypeRepository repository;
    private SenderTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemorySenderTypeRepository();
        service = new SenderTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        SenderType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        SenderTypeCommand invalid = new SenderTypeCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(SenderTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        SenderType created = service.create(commandA());
        SenderTypeCommand nuevo = commandB();

        SenderType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameType(), updated.nameType());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(SenderTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        SenderType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(SenderTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private SenderTypeCommand commandA() {
        return new SenderTypeCommand(
                "valor-a");
    }

    private SenderTypeCommand commandB() {
        return new SenderTypeCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemorySenderTypeRepository implements SenderTypeRepository {

        private final Map<UUID, SenderType> store = new HashMap<>();

        @Override
        public SenderType save(SenderType senderType) {
            store.put(senderType.id(), senderType);
            return senderType;
        }

        @Override
        public Optional<SenderType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<SenderType> findAll() {
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
