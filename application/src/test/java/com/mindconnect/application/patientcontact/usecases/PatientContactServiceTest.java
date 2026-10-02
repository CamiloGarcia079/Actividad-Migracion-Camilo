package com.mindconnect.application.patientcontact.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.patientcontact.dtos.PatientContactCommand;
import com.mindconnect.application.patientcontact.exceptions.PatientContactNotFoundException;
import com.mindconnect.domain.patientcontact.model.aggregates.PatientContact;
import com.mindconnect.domain.patientcontact.repositories.PatientContactRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientContactServiceTest {

    private InMemoryPatientContactRepository repository;
    private PatientContactService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPatientContactRepository();
        service = new PatientContactService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        PatientContact created = service.create(commandA());

        assertNotNull(created.id());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        PatientContactCommand invalid = new PatientContactCommand(
                null,
                UUID.randomUUID(),
                true,
                true,
                UUID.randomUUID());

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientContactNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        PatientContact created = service.create(commandA());
        PatientContactCommand nuevo = commandB();

        PatientContact updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(nuevo.contactId(), updated.contactId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientContactNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        PatientContact created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(PatientContactNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private PatientContactCommand commandA() {
        return new PatientContactCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                true,
                true,
                UUID.randomUUID());
    }

    private PatientContactCommand commandB() {
        return new PatientContactCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                false,
                false,
                UUID.randomUUID());
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryPatientContactRepository implements PatientContactRepository {

        private final Map<UUID, PatientContact> store = new HashMap<>();

        @Override
        public PatientContact save(PatientContact patientContact) {
            store.put(patientContact.id(), patientContact);
            return patientContact;
        }

        @Override
        public Optional<PatientContact> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<PatientContact> findAll() {
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
