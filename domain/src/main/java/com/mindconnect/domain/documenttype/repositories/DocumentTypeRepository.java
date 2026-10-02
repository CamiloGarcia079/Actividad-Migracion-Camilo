package com.mindconnect.domain.documenttype.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mindconnect.domain.documenttype.model.aggregates.DocumentType;

/**
 * Puerto de salida: lo que el dominio necesita para guardar y consultar document_types.
 * Lo implementa un adaptador de la capa de infraestructura.
 */
public interface DocumentTypeRepository {

    DocumentType save(DocumentType documentType);

    Optional<DocumentType> findById(UUID id);

    List<DocumentType> findAll();

    boolean existsById(UUID id);

    void deleteById(UUID id);
}
