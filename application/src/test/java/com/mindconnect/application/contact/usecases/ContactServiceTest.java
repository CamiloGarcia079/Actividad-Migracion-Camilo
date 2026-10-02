package com.mindconnect.application.contact.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.contact.dtos.ContactCommand;
import com.mindconnect.application.contact.exceptions.ContactNotFoundException;
import com.mindconnect.domain.contact.model.aggregates.Contact;
import com.mindconnect.domain.contact.repositories.ContactRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactServiceTest {

    private InMemoryContactRepository repository;
    private ContactService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryContactRepository();
        service = new ContactService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        Contact created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ContactCommand invalid = new ContactCommand(
                null,
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ContactNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        Contact created = service.create(commandA());
        ContactCommand nuevo = commandB();

        Contact updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.fullName(), updated.fullName());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ContactNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        Contact created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ContactNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ContactCommand commandA() {
        return new ContactCommand(
                "valor-a",
                "valor-a",
                "valor-a",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    private ContactCommand commandB() {
        return new ContactCommand(
                "valor-b",
                "valor-b",
                "valor-b",
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryContactRepository implements ContactRepository {

        private final Map<UUID, Contact> store = new HashMap<>();

        @Override
        public Contact save(Contact contact) {
            store.put(contact.id(), contact);
            return contact;
        }

        @Override
        public Optional<Contact> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<Contact> findAll() {
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
