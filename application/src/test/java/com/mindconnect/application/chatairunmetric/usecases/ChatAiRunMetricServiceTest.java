package com.mindconnect.application.chatairunmetric.usecases;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatairunmetric.dtos.ChatAiRunMetricCommand;
import com.mindconnect.application.chatairunmetric.exceptions.ChatAiRunMetricNotFoundException;
import com.mindconnect.domain.chatairunmetric.model.aggregates.ChatAiRunMetric;
import com.mindconnect.domain.chatairunmetric.repositories.ChatAiRunMetricRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatAiRunMetricServiceTest {

    private InMemoryChatAiRunMetricRepository repository;
    private ChatAiRunMetricService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatAiRunMetricRepository();
        service = new ChatAiRunMetricService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatAiRunMetric created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatAiRunMetricCommand invalid = new ChatAiRunMetricCommand(
                null,
                1,
                1,
                1,
                new BigDecimal("1.5"));

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunMetricNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatAiRunMetric created = service.create(commandA());
        ChatAiRunMetricCommand nuevo = commandB();

        ChatAiRunMetric updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.aiRunId(), updated.aiRunId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunMetricNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatAiRunMetric created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatAiRunMetricNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatAiRunMetricCommand commandA() {
        return new ChatAiRunMetricCommand(
                UUID.randomUUID(),
                1,
                1,
                1,
                new BigDecimal("1.5"));
    }

    private ChatAiRunMetricCommand commandB() {
        return new ChatAiRunMetricCommand(
                UUID.randomUUID(),
                2,
                2,
                2,
                new BigDecimal("2.5"));
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatAiRunMetricRepository implements ChatAiRunMetricRepository {

        private final Map<UUID, ChatAiRunMetric> store = new HashMap<>();

        @Override
        public ChatAiRunMetric save(ChatAiRunMetric chatAiRunMetric) {
            store.put(chatAiRunMetric.id(), chatAiRunMetric);
            return chatAiRunMetric;
        }

        @Override
        public Optional<ChatAiRunMetric> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatAiRunMetric> findAll() {
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
