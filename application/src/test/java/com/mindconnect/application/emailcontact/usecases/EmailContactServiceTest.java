package com.mindconnect.application.emailcontact.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.emailcontact.dtos.EmailContactCommand;
import com.mindconnect.application.emailcontact.exceptions.EmailContactNotFoundException;
import com.mindconnect.domain.emailcontact.model.aggregates.EmailContact;
import com.mindconnect.domain.emailcontact.repositories.EmailContactRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailContactServiceTest {

    private InMemoryEmailContactRepository repository;
    private EmailContactService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEmailContactRepository();
        service = new EmailContactService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        EmailContact created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        EmailContactCommand invalid = new EmailContactCommand(
                null,
                "valor-a",
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(EmailContactNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        EmailContact created = service.create(commandA());
        EmailContactCommand nuevo = commandB();

        EmailContact updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.contactId(), updated.contactId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(EmailContactNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        EmailContact created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(EmailContactNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private EmailContactCommand commandA() {
        return new EmailContactCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a");
    }

    private EmailContactCommand commandB() {
        return new EmailContactCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryEmailContactRepository implements EmailContactRepository {

        private final Map<UUID, EmailContact> store = new HashMap<>();

        @Override
        public EmailContact save(EmailContact emailContact) {
            store.put(emailContact.id(), emailContact);
            return emailContact;
        }

        @Override
        public Optional<EmailContact> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<EmailContact> findAll() {
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
