package com.mindconnect.application.relationshiptype.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.relationshiptype.dtos.RelationshipTypeCommand;
import com.mindconnect.application.relationshiptype.exceptions.RelationshipTypeNotFoundException;
import com.mindconnect.domain.relationshiptype.model.aggregates.RelationshipType;
import com.mindconnect.domain.relationshiptype.repositories.RelationshipTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelationshipTypeServiceTest {

    private InMemoryRelationshipTypeRepository repository;
    private RelationshipTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRelationshipTypeRepository();
        service = new RelationshipTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        RelationshipType created = service.create(commandA());

        assertNotNull(created.id());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        RelationshipTypeCommand invalid = new RelationshipTypeCommand(
                null);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(RelationshipTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        RelationshipType created = service.create(commandA());
        RelationshipTypeCommand nuevo = commandB();

        RelationshipType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(nuevo.description(), updated.description());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(RelationshipTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        RelationshipType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(RelationshipTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private RelationshipTypeCommand commandA() {
        return new RelationshipTypeCommand(
                "valor-a");
    }

    private RelationshipTypeCommand commandB() {
        return new RelationshipTypeCommand(
                "valor-b");
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryRelationshipTypeRepository implements RelationshipTypeRepository {

        private final Map<UUID, RelationshipType> store = new HashMap<>();

        @Override
        public RelationshipType save(RelationshipType relationshipType) {
            store.put(relationshipType.id(), relationshipType);
            return relationshipType;
        }

        @Override
        public Optional<RelationshipType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<RelationshipType> findAll() {
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
