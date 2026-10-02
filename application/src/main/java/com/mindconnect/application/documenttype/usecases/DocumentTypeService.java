package com.mindconnect.application.documenttype.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mindconnect.application.documenttype.dtos.DocumentTypeCommand;
import com.mindconnect.application.documenttype.exceptions.DocumentTypeNotFoundException;
import com.mindconnect.application.documenttype.ports.in.DocumentTypeUseCase;
import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;
import com.mindconnect.domain.documenttype.repositories.DocumentTypeRepository;

/**
 * Implementación de los casos de uso de document_types.
 * No usa Spring: lo que necesita (el repositorio) le llega por el constructor.
 */
public class DocumentTypeService implements DocumentTypeUseCase {

    private final DocumentTypeRepository repository;

    public DocumentTypeService(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentType create(DocumentTypeCommand command) {
        LocalDateTime now = LocalDateTime.now();
        DocumentType documentType = new DocumentType(
                UUID.randomUUID(),
                command.code(),
                command.name(),
                command.active(),
                now,
                now);
        return repository.save(documentType);
    }

    @Override
    public DocumentType getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DocumentTypeNotFoundException(id));
    }

    @Override
    public List<DocumentType> getAll() {
        return repository.findAll();
    }

    @Override
    public DocumentType update(UUID id, DocumentTypeCommand command) {
        DocumentType existing = getById(id);
        LocalDateTime now = LocalDateTime.now();
        DocumentType updated = new DocumentType(
                existing.id(),
                command.code(),
                command.name(),
                command.active(),
                existing.createdAt(),
                now);
        return repository.save(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new DocumentTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
