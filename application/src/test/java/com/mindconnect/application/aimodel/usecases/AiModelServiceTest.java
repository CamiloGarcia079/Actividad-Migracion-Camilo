package com.mindconnect.application.aimodel.usecases;

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

import com.mindconnect.application.aimodel.dtos.AiModelCommand;
import com.mindconnect.application.aimodel.exceptions.AiModelNotFoundException;
import com.mindconnect.domain.aimodel.model.aggregates.AiModel;
import com.mindconnect.domain.aimodel.repositories.AiModelRepository;
import com.mindconnect.domain.shared.exceptions.DomainException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AiModelServiceTest {

    private InMemoryAiModelRepository repository;
    private AiModelService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryAiModelRepository();
        service = new AiModelService(repository);
    }

    @Test
    void crearGuardaElRegistroConIdGenerado() {
        AiModel created = service.create(commandA());

        assertNotNull(created.id());
        assertNotNull(created.createdAt());
        assertNotNull(created.updatedAt());
        assertTrue(repository.existsById(created.id()));
    }

    @Test
    void crearConUnCampoObligatorioNuloLanzaDomainException() {
        AiModelCommand invalid = new AiModelCommand(
                null,
                "valor-a",
                "valor-a",
                new BigDecimal("1.5"),
                new BigDecimal("1.5"),
                1,
                1,
                true);

        assertThrows(DomainException.class, () -> service.create(invalid));
    }

    @Test
    void buscarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiModelNotFoundException.class, () -> service.getById(UUID.randomUUID()));
    }

    @Test
    void listarDevuelveLoGuardado() {
        service.create(commandA());
        service.create(commandB());

        assertEquals(2, service.getAll().size());
    }

    @Test
    void actualizarConservaElIdYCambiaLosDatos() {
        AiModel created = service.create(commandA());
        AiModelCommand nuevo = commandB();

        AiModel updated = service.update(created.id(), nuevo);

        assertEquals(created.id(), updated.id());
        assertEquals(created.createdAt(), updated.createdAt());
        assertEquals(nuevo.providerModelId(), updated.providerModelId());
        assertEquals(updated, service.getById(created.id()));
    }

    @Test
    void actualizarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiModelNotFoundException.class, () -> service.update(UUID.randomUUID(), commandA()));
    }

    @Test
    void eliminarBorraElRegistro() {
        AiModel created = service.create(commandA());

        service.delete(created.id());

        assertFalse(repository.existsById(created.id()));
    }

    @Test
    void eliminarUnIdInexistenteLanzaNotFound() {
        assertThrows(AiModelNotFoundException.class, () -> service.delete(UUID.randomUUID()));
    }

    private AiModelCommand commandA() {
        return new AiModelCommand(
                UUID.randomUUID(),
                "valor-a",
                "valor-a",
                new BigDecimal("1.5"),
                new BigDecimal("1.5"),
                1,
                1,
                true);
    }

    private AiModelCommand commandB() {
        return new AiModelCommand(
                UUID.randomUUID(),
                "valor-b",
                "valor-b",
                new BigDecimal("2.5"),
                new BigDecimal("2.5"),
                2,
                2,
                false);
    }

    /** Repositorio falso en memoria: así se prueba el caso de uso sin base de datos. */
    private static class InMemoryAiModelRepository implements AiModelRepository {

        private final Map<UUID, AiModel> store = new HashMap<>();

        @Override
        public AiModel save(AiModel aiModel) {
            store.put(aiModel.id(), aiModel);
            return aiModel;
        }

        @Override
        public Optional<AiModel> findById(UUID id) {
            return Optional.ofNullable(store.get(id));
        }

        @Override
        public List<AiModel> findAll() {
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
