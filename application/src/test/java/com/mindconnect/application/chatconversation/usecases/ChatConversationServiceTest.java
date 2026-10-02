package com.mindconnect.application.chatconversation.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversation.dtos.ChatConversationCommand;
import com.mindconnect.application.chatconversation.exceptions.ChatConversationNotFoundException;
import com.mindconnect.domain.chatconversation.model.aggregates.ChatConversation;
import com.mindconnect.domain.chatconversation.repositories.ChatConversationRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatConversationServiceTest {

    private InMemoryChatConversationRepository repository;
    private ChatConversationService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatConversationRepository();
        service = new ChatConversationService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatConversation created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatConversationCommand invalid = new ChatConversationCommand(
                null,
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                true,
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatConversation created = service.create(commandA());
        ChatConversationCommand nuevo = commandB();

        ChatConversation updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationStatusId(), updated.conversationStatusId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatConversation created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatConversationCommand commandA() {
        return new ChatConversationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0),
                true,
                LocalDateTime.of(2026, 1, 15, 10, 0),
                UUID.randomUUID());
    }

    private ChatConversationCommand commandB() {
        return new ChatConversationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 2, 20, 8, 30),
                false,
                LocalDateTime.of(2026, 2, 20, 8, 30),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatConversationRepository implements ChatConversationRepository {

        private final Map<UUID, ChatConversation> store = new HashMap<>();

        @Override
        public ChatConversation save(ChatConversation chatConversation) {
            store.put(chatConversation.id(), chatConversation);
            return chatConversation;
        }

        @Override
        public Optional<ChatConversation> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatConversation> findAll() {
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
