package com.mindconnect.application.chatparticipant.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatparticipant.dtos.ChatParticipantCommand;
import com.mindconnect.application.chatparticipant.exceptions.ChatParticipantNotFoundException;
import com.mindconnect.domain.chatparticipant.model.aggregates.ChatParticipant;
import com.mindconnect.domain.chatparticipant.repositories.ChatParticipantRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatParticipantServiceTest {

    private InMemoryChatParticipantRepository repository;
    private ChatParticipantService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatParticipantRepository();
        service = new ChatParticipantService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatParticipant created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatParticipantCommand invalid = new ChatParticipantCommand(
                null,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatParticipantNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatParticipant created = service.create(commandA());
        ChatParticipantCommand nuevo = commandB();

        ChatParticipant updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationId(), updated.conversationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatParticipantNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatParticipant created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatParticipantNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatParticipantCommand commandA() {
        return new ChatParticipantCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private ChatParticipantCommand commandB() {
        return new ChatParticipantCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatParticipantRepository implements ChatParticipantRepository {

        private final Map<UUID, ChatParticipant> store = new HashMap<>();

        @Override
        public ChatParticipant save(ChatParticipant chatParticipant) {
            store.put(chatParticipant.id(), chatParticipant);
            return chatParticipant;
        }

        @Override
        public Optional<ChatParticipant> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatParticipant> findAll() {
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
