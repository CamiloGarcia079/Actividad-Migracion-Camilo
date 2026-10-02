package com.mindconnect.application.professionaltype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.professionaltype.dtos.ProfessionalTypeCommand;
import com.mindconnect.application.professionaltype.exceptions.ProfessionalTypeNotFoundException;
import com.mindconnect.domain.professionaltype.model.aggregates.ProfessionalType;
import com.mindconnect.domain.professionaltype.repositories.ProfessionalTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfessionalTypeServiceTest {

    private InMemoryProfessionalTypeRepository repository;
    private ProfessionalTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProfessionalTypeRepository();
        service = new ProfessionalTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        ProfessionalType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        ProfessionalTypeCommand invalid = new ProfessionalTypeCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        ProfessionalType created = service.create(commandA());
        ProfessionalTypeCommand nuevo = commandB();

        ProfessionalType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.name(), updated.name());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        ProfessionalType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(ProfessionalTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private ProfessionalTypeCommand commandA() {
        return new ProfessionalTypeCommand(
                "valor-a");
    }

    private ProfessionalTypeCommand commandB() {
        return new ProfessionalTypeCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryProfessionalTypeRepository implements ProfessionalTypeRepository {

        private final Map<UUID, ProfessionalType> store = new HashMap<>();

        @Override
        public ProfessionalType save(ProfessionalType professionalType) {
            store.put(professionalType.id(), professionalType);
            return professionalType;
        }

        @Override
        public Optional<ProfessionalType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<ProfessionalType> findAll() {
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
