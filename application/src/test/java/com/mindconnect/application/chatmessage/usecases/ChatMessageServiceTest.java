package com.mindconnect.application.chatmessage.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatmessage.dtos.ChatMessageCommand;
import com.mindconnect.application.chatmessage.exceptions.ChatMessageNotFoundException;
import com.mindconnect.domain.chatmessage.model.aggregates.ChatMessage;
import com.mindconnect.domain.chatmessage.repositories.ChatMessageRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatMessageServiceTest {

    private InMemoryChatMessageRepository repository;
    private ChatMessageService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatMessageRepository();
        service = new ChatMessageService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatMessage created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatMessageCommand invalid = new ChatMessageCommand(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "{}",
                "{}");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatMessageNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatMessage created = service.create(commandA());
        ChatMessageCommand nuevo = commandB();

        ChatMessage updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationId(), updated.conversationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatMessageNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatMessage created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatMessageNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatMessageCommand commandA() {
        return new ChatMessageCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "{}",
                "{}");
    }

    private ChatMessageCommand commandB() {
        return new ChatMessageCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "{\"b\":1}",
                "{\"b\":1}");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatMessageRepository implements ChatMessageRepository {

        private final Map<UUID, ChatMessage> store = new HashMap<>();

        @Override
        public ChatMessage save(ChatMessage chatMessage) {
            store.put(chatMessage.id(), chatMessage);
            return chatMessage;
        }

        @Override
        public Optional<ChatMessage> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatMessage> findAll() {
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
