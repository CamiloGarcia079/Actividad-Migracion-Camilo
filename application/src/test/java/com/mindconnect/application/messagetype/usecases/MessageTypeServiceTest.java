package com.mindconnect.application.messagetype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.messagetype.dtos.MessageTypeCommand;
import com.mindconnect.application.messagetype.exceptions.MessageTypeNotFoundException;
import com.mindconnect.domain.messagetype.model.aggregates.MessageType;
import com.mindconnect.domain.messagetype.repositories.MessageTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageTypeServiceTest {

    private InMemoryMessageTypeRepository repository;
    private MessageTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMessageTypeRepository();
        service = new MessageTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        MessageType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        MessageTypeCommand invalid = new MessageTypeCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(MessageTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        MessageType created = service.create(commandA());
        MessageTypeCommand nuevo = commandB();

        MessageType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.nameType(), updated.nameType());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(MessageTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        MessageType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(MessageTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private MessageTypeCommand commandA() {
        return new MessageTypeCommand(
                "valor-a");
    }

    private MessageTypeCommand commandB() {
        return new MessageTypeCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryMessageTypeRepository implements MessageTypeRepository {

        private final Map<UUID, MessageType> store = new HashMap<>();

        @Override
        public MessageType save(MessageType messageType) {
            store.put(messageType.id(), messageType);
            return messageType;
        }

        @Override
        public Optional<MessageType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<MessageType> findAll() {
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
