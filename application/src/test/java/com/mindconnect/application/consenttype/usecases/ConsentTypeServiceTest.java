package com.mindconnect.application.consenttype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.consenttype.dtos.ConsentTypeCommand;
import com.mindconnect.application.consenttype.exceptions.ConsentTypeNotFoundException;
import com.mindconnect.domain.consenttype.model.aggregates.ConsentType;
import com.mindconnect.domain.consenttype.repositories.ConsentTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsentTypeServiceTest {

    private InMemoryConsentTypeRepository repository;
    private ConsentTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryConsentTypeRepository();
        service = new ConsentTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ConsentType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ConsentTypeCommand invalid = new ConsentTypeCommand(
                null,
                "valor-a",
                true,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConsentTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ConsentType created = service.create(commandA());
        ConsentTypeCommand nuevo = commandB();

        ConsentType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConsentTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ConsentType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConsentTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ConsentTypeCommand commandA() {
        return new ConsentTypeCommand(
                "valor-a",
                "valor-a",
                true,
                "valor-a");
    }

    private ConsentTypeCommand commandB() {
        return new ConsentTypeCommand(
                "valor-b",
                "valor-b",
                false,
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryConsentTypeRepository implements ConsentTypeRepository {

        private final Map<UUID, ConsentType> store = new HashMap<>();

        @Override
        public ConsentType save(ConsentType consentType) {
            store.put(consentType.id(), consentType);
            return consentType;
        }

        @Override
        public Optional<ConsentType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConsentType> findAll() {
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
