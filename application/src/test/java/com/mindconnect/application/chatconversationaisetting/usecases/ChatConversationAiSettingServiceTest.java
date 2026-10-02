package com.mindconnect.application.chatconversationaisetting.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.chatconversationaisetting.dtos.ChatConversationAiSettingCommand;
import com.mindconnect.application.chatconversationaisetting.exceptions.ChatConversationAiSettingNotFoundException;
import com.mindconnect.domain.chatconversationaisetting.model.aggregates.ChatConversationAiSetting;
import com.mindconnect.domain.chatconversationaisetting.repositories.ChatConversationAiSettingRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatConversationAiSettingServiceTest {

    private InMemoryChatConversationAiSettingRepository repository;
    private ChatConversationAiSettingService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryChatConversationAiSettingRepository();
        service = new ChatConversationAiSettingService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ChatConversationAiSetting created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ChatConversationAiSettingCommand invalid = new ChatConversationAiSettingCommand(
                null,
                true,
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationAiSettingNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ChatConversationAiSetting created = service.create(commandA());
        ChatConversationAiSettingCommand nuevo = commandB();

        ChatConversationAiSetting updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.conversationId(), updated.conversationId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationAiSettingNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ChatConversationAiSetting created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ChatConversationAiSettingNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ChatConversationAiSettingCommand commandA() {
        return new ChatConversationAiSettingCommand(
                UUID.randomUUID(),
                true,
                UUID.randomUUID());
    }

    private ChatConversationAiSettingCommand commandB() {
        return new ChatConversationAiSettingCommand(
                UUID.randomUUID(),
                false,
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryChatConversationAiSettingRepository implements ChatConversationAiSettingRepository {

        private final Map<UUID, ChatConversationAiSetting> store = new HashMap<>();

        @Override
        public ChatConversationAiSetting save(ChatConversationAiSetting chatConversationAiSetting) {
            store.put(chatConversationAiSetting.id(), chatConversationAiSetting);
            return chatConversationAiSetting;
        }

        @Override
        public Optional<ChatConversationAiSetting> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ChatConversationAiSetting> findAll() {
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
