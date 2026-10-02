package com.mindconnect.application.documenttype.ports.in;

import java.util.List;
import java.util.UUID;

import com.mindconnect.application.documenttype.dtos.DocumentTypeCommand;
import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;

/**
 * Puerto de entrada: lo que la aplicación ofrece sobre document_types.
 */
public interface DocumentTypeUseCase {

    DocumentType create(DocumentTypeCommand command);

    DocumentType getById(UUID id);

    List<DocumentType> getAll();

    DocumentType update(UUID id, DocumentTypeCommand command);

    void delete(UUID id);
}
