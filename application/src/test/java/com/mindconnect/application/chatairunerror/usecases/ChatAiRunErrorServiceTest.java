package com.mindconnect.application.chatairunerror.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunerror.dtos.ChatAiRunErrorCommand;
import com.mindconnect.application.chatairunerror.exceptions.ChatAiRunErrorNotFoundException;
import com.mindconnect.domain.chatairunerror.model.aggregates.ChatAiRunError;
import com.mindconnect.domain.chatairunerror.repositories.ChatAiRunErrorRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatAiRunErrorServiceTest {

    private InMemoryChatAiRunErrorRepository repository;
    private ChatAiRunErrorService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatAiRunErrorRepository();
        service = new ChatAiRunErrorService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatAiRunError created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatAiRunErrorCommand invalid = new ChatAiRunErrorCommand(
                null,
                "valor-a",
                "valor-a",
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunErrorNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatAiRunError created = service.create(commandA());
        ChatAiRunErrorCommand nuevo = commandB();

        ChatAiRunError updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.aiRunId(), updated.aiRunId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunErrorNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatAiRunError created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunErrorNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatAiRunErrorCommand commandA() {
        return new ChatAiRunErrorCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                "valor-a");
    }

    private ChatAiRunErrorCommand commandB() {
        return new ChatAiRunErrorCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatAiRunErrorRepository implements ChatAiRunErrorRepository {

        private final Map<UUID, ChatAiRunError> store = new HashMap<>();

        @Override
        public ChatAiRunError save(ChatAiRunError chatAiRunError) {
            store.put(chatAiRunError.id(), chatAiRunError);
            return chatAiRunError;
        }

        @Override
        public Optional<ChatAiRunError> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunError> findAll() {
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
