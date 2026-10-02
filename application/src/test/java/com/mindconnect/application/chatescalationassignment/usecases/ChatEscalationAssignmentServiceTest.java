package com.mindconnect.application.chatescalationassignment.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalationassignment.dtos.ChatEscalationAssignmentCommand;
import com.mindconnect.application.chatescalationassignment.exceptions.ChatEscalationAssignmentNotFoundException;
import com.mindconnect.domain.chatescalationassignment.model.aggregates.ChatEscalationAssignment;
import com.mindconnect.domain.chatescalationassignment.repositories.ChatEscalationAssignmentRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatEscalationAssignmentServiceTest {

    private InMemoryChatEscalationAssignmentRepository repository;
    private ChatEscalationAssignmentService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatEscalationAssignmentRepository();
        service = new ChatEscalationAssignmentService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatEscalationAssignment created = service.create(commandA());

        assertNotNull(created.id());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatEscalationAssignmentCommand invalid = new ChatEscalationAssignmentCommand(
                null,
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0));

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationAssignmentNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatEscalationAssignment created = service.create(commandA());
        ChatEscalationAssignmentCommand nuevo = commandB();

        ChatEscalationAssignment updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(nuevo.escalationId(), updated.escalationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationAssignmentNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatEscalationAssignment created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationAssignmentNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatEscalationAssignmentCommand commandA() {
        return new ChatEscalationAssignmentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 1, 15, 10, 0));
    }

    private ChatEscalationAssignmentCommand commandB() {
        return new ChatEscalationAssignmentCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2026, 2, 20, 8, 30));
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatEscalationAssignmentRepository implements ChatEscalationAssignmentRepository {

        private final Map<UUID, ChatEscalationAssignment> store = new HashMap<>();

        @Override
        public ChatEscalationAssignment save(ChatEscalationAssignment chatEscalationAssignment) {
            store.put(chatEscalationAssignment.id(), chatEscalationAssignment);
            return chatEscalationAssignment;
        }

        @Override
        public Optional<ChatEscalationAssignment> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalationAssignment> findAll() {
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
