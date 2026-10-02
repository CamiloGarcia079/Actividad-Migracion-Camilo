package com.mindconnect.application.documenttype.usecases;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mindconnect.application.documenttype.dtos.DocumentTypeCommand;
import com.mindconnect.application.documenttype.exceptions.DocumentTypeNotFoundException;
import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;
import com.mindconnect.domain.documenttype.repositories.DocumentTypeRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentTypeServiceTest {

    private InMemoryDocumentTypeRepository repository;
    private DocumentTypeService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryDocumentTypeRepository();
        service = new DocumentTypeService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        DocumentType created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        DocumentTypeCommand invalid = new DocumentTypeCommand(
                null,
                "valor-a",
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(DocumentTypeNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        DocumentType created = service.create(commandA());
        DocumentTypeCommand nuevo = commandB();

        DocumentType updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.code(), updated.code());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(DocumentTypeNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        DocumentType created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(DocumentTypeNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private DocumentTypeCommand commandA() {
        return new DocumentTypeCommand(
                "valor-a",
                "valor-a",
                true);
    }

    private DocumentTypeCommand commandB() {
        return new DocumentTypeCommand(
                "valor-b",
                "valor-b",
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryDocumentTypeRepository implements DocumentTypeRepository {

        private final Map<UUID, DocumentType> store = new HashMap<>();

        @Override
        public DocumentType save(DocumentType documentType) {
            store.put(documentType.id(), documentType);
            return documentType;
        }

        @Override
        public Optional<DocumentType> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<DocumentType> findAll() {
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
