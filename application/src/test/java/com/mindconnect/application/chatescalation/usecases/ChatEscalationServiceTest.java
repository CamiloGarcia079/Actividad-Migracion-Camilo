package com.mindconnect.application.chatescalation.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatescalation.dtos.ChatEscalationCommand;
import com.mindconnect.application.chatescalation.exceptions.ChatEscalationNotFoundException;
import com.mindconnect.domain.chatescalation.model.aggregates.ChatEscalation;
import com.mindconnect.domain.chatescalation.repositories.ChatEscalationRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatEscalationServiceTest {

    private InMemoryChatEscalationRepository repository;
    private ChatEscalationService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatEscalationRepository();
        service = new ChatEscalationService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatEscalation created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatEscalationCommand invalid = new ChatEscalationCommand(
                null,
                UUID.randomUUID(),
                true,
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatEscalation created = service.create(commandA());
        ChatEscalationCommand nuevo = commandB();

        ChatEscalation updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationId(), updated.conversationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatEscalation created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatEscalationNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatEscalationCommand commandA() {
        return new ChatEscalationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                true,
                "valor-a");
    }

    private ChatEscalationCommand commandB() {
        return new ChatEscalationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                false,
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatEscalationRepository implements ChatEscalationRepository {

        private final Map<UUID, ChatEscalation> store = new HashMap<>();

        @Override
        public ChatEscalation save(ChatEscalation chatEscalation) {
            store.put(chatEscalation.id(), chatEscalation);
            return chatEscalation;
        }

        @Override
        public Optional<ChatEscalation> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatEscalation> findAll() {
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
