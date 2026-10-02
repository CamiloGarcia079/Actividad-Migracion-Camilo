package com.mindconnect.application.chatairun.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairun.dtos.ChatAiRunCommand;
import com.mindconnect.application.chatairun.exceptions.ChatAiRunNotFoundException;
import com.mindconnect.domain.chatairun.model.aggregates.ChatAiRun;
import com.mindconnect.domain.chatairun.repositories.ChatAiRunRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatAiRunServiceTest {

    private InMemoryChatAiRunRepository repository;
    private ChatAiRunService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatAiRunRepository();
        service = new ChatAiRunService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatAiRun created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatAiRunCommand invalid = new ChatAiRunCommand(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatAiRun created = service.create(commandA());
        ChatAiRunCommand nuevo = commandB();

        ChatAiRun updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationId(), updated.conversationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatAiRun created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatAiRunCommand commandA() {
        return new ChatAiRunCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private ChatAiRunCommand commandB() {
        return new ChatAiRunCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatAiRunRepository implements ChatAiRunRepository {

        private final Map<UUID, ChatAiRun> store = new HashMap<>();

        @Override
        public ChatAiRun save(ChatAiRun chatAiRun) {
            store.put(chatAiRun.id(), chatAiRun);
            return chatAiRun;
        }

        @Override
        public Optional<ChatAiRun> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRun> findAll() {
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
