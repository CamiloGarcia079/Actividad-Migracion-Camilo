package com.mindconnect.infrastructure.documenttype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindconnect.application.documenttype.ports.in.DocumentTypeUseCase;
import com.mindconnect.infrastructure.documenttype.adapters.in.rest.dtos.DocumentTypeRequest;
import com.mindconnect.infrastructure.documenttype.adapters.in.rest.dtos.DocumentTypeResponse;

/**
 * Adaptador de entrada REST para document_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeUseCase useCase;

    public DocumentTypeController(DocumentTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<DocumentTypeResponse> create(@Valid @RequestBody DocumentTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(DocumentTypeResponse.from(created));
    }

    @GetMapping
    public List<DocumentTypeResponse> getAll() {
        return useCase.getAll().stream().map(DocumentTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public DocumentTypeResponse getById(@PathVariable UUID id) {
        return DocumentTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public DocumentTypeResponse update(@PathVariable UUID id, @Valid @RequestBody DocumentTypeRequest request) {
        return DocumentTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
