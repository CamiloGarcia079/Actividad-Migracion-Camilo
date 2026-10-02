package com.mindconnect.application.phonecontact.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.phonecontact.dtos.PhoneContactCommand;
import com.mindconnect.application.phonecontact.exceptions.PhoneContactNotFoundException;
import com.mindconnect.domain.phonecontact.model.aggregates.PhoneContact;
import com.mindconnect.domain.phonecontact.repositories.PhoneContactRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneContactServiceTest {

    private InMemoryPhoneContactRepository repository;
    private PhoneContactService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPhoneContactRepository();
        service = new PhoneContactService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        PhoneContact created = service.create(commandA());

        assertNotNull(created.id());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        PhoneContactCommand invalid = new PhoneContactCommand(
                null,
                "valor-a",
                "valor-a");

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PhoneContactNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        PhoneContact created = service.create(commandA());
        PhoneContactCommand nuevo = commandB();

        PhoneContact updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(nuevo.contactId(), updated.contactId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PhoneContactNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        PhoneContact created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(PhoneContactNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private PhoneContactCommand commandA() {
        return new PhoneContactCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a");
    }

    private PhoneContactCommand commandB() {
        return new PhoneContactCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryPhoneContactRepository implements PhoneContactRepository {

        private final Map<UUID, PhoneContact> store = new HashMap<>();

        @Override
        public PhoneContact save(PhoneContact phoneContact) {
            store.put(phoneContact.id(), phoneContact);
            return phoneContact;
        }

        @Override
        public Optional<PhoneContact> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PhoneContact> findAll() {
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
