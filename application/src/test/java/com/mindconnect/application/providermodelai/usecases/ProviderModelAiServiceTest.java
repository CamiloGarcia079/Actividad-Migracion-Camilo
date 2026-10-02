package com.mindconnect.application.providermodelai.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.providermodelai.dtos.ProviderModelAiCommand;
import com.mindconnect.application.providermodelai.exceptions.ProviderModelAiNotFoundException;
import com.mindconnect.domain.providermodelai.model.aggregates.ProviderModelAi;
import com.mindconnect.domain.providermodelai.repositories.ProviderModelAiRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProviderModelAiServiceTest {

    private InMemoryProviderModelAiRepository repository;
    private ProviderModelAiService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProviderModelAiRepository();
        service = new ProviderModelAiService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ProviderModelAi created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ProviderModelAiCommand invalid = new ProviderModelAiCommand(
                null,
                "valor-a",
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProviderModelAiNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ProviderModelAi created = service.create(commandA());
        ProviderModelAiCommand nuevo = commandB();

        ProviderModelAi updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameProviderAi(), updated.nameProviderAi());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProviderModelAiNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ProviderModelAi created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProviderModelAiNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ProviderModelAiCommand commandA() {
        return new ProviderModelAiCommand(
                "valor-a",
                "valor-a",
                "valor-a",
                true);
    }

    private ProviderModelAiCommand commandB() {
        return new ProviderModelAiCommand(
                "valor-b",
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryProviderModelAiRepository implements ProviderModelAiRepository {

        private final Map<UUID, ProviderModelAi> store = new HashMap<>();

        @Override
        public ProviderModelAi save(ProviderModelAi providerModelAi) {
            store.put(providerModelAi.id(), providerModelAi);
            return providerModelAi;
        }

        @Override
        public Optional<ProviderModelAi> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProviderModelAi> findAll() {
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
