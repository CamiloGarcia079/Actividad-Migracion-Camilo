package com.mindconnect.application.conversationstatus.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.conversationstatus.dtos.ConversationStatusCommand;
import com.mindconnect.application.conversationstatus.exceptions.ConversationStatusNotFoundException;
import com.mindconnect.domain.conversationstatus.model.aggregates.ConversationStatus;
import com.mindconnect.domain.conversationstatus.repositories.ConversationStatusRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConversationStatusServiceTest {

    private InMemoryConversationStatusRepository repository;
    private ConversationStatusService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryConversationStatusRepository();
        service = new ConversationStatusService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ConversationStatus created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ConversationStatusCommand invalid = new ConversationStatusCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConversationStatusNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ConversationStatus created = service.create(commandA());
        ConversationStatusCommand nuevo = commandB();

        ConversationStatus updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameStatus(), updated.nameStatus());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConversationStatusNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ConversationStatus created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ConversationStatusNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ConversationStatusCommand commandA() {
        return new ConversationStatusCommand(
                "valor-a");
    }

    private ConversationStatusCommand commandB() {
        return new ConversationStatusCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryConversationStatusRepository implements ConversationStatusRepository {

        private final Map<UUID, ConversationStatus> store = new HashMap<>();

        @Override
        public ConversationStatus save(ConversationStatus conversationStatus) {
            store.put(conversationStatus.id(), conversationStatus);
            return conversationStatus;
        }

        @Override
        public Optional<ConversationStatus> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ConversationStatus> findAll() {
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
