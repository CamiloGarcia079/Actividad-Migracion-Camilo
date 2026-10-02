package com.mindconnect.application.chatescalationstatushistory.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationstatushistory.dtos.ChatEscalationStatusHistoryCommand;
import com.mindconnect.application.chatescalationstatushistory.exceptions.ChatEscalationStatusHistoryNotFoundException;
import com.mindconnect.domain.chatescalationstatushistory.model.aggregates.ChatEscalationStatusHistory;
import com.mindconnect.domain.chatescalationstatushistory.repositories.ChatEscalationStatusHistoryRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatEscalationStatusHistoryServiceTest {

    private InMemoryChatEscalationStatusHistoryRepository repository;
    private ChatEscalationStatusHistoryService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatEscalationStatusHistoryRepository();
        service = new ChatEscalationStatusHistoryService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatEscalationStatusHistory created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatEscalationStatusHistoryCommand invalid = new ChatEscalationStatusHistoryCommand(
                null,
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0));

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationStatusHistoryNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatEscalationStatusHistory created = service.create(commandA());
        ChatEscalationStatusHistoryCommand nuevo = commandB();

        ChatEscalationStatusHistory updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.escalationId(), updated.escalationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationStatusHistoryNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatEscalationStatusHistory created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationStatusHistoryNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatEscalationStatusHistoryCommand commandA() {
        return new ChatEscalationStatusHistoryCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0));
    }

    private ChatEscalationStatusHistoryCommand commandB() {
        return new ChatEscalationStatusHistoryCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 2, 20, 8, 30));
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatEscalationStatusHistoryRepository implements ChatEscalationStatusHistoryRepository {

        private final Map<UUID, ChatEscalationStatusHistory> store = new HashMap<>();

        @Override
        public ChatEscalationStatusHistory save(ChatEscalationStatusHistory chatEscalationStatusHistory) {
            store.put(chatEscalationStatusHistory.id(), chatEscalationStatusHistory);
            return chatEscalationStatusHistory;
        }

        @Override
        public Optional<ChatEscalationStatusHistory> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalationStatusHistory> findAll() {
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
