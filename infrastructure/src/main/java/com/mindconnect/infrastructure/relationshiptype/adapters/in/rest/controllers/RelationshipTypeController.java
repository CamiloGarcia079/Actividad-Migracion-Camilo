package com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.controllers;

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

import com.mindconnect.application.relationshiptype.ports.in.RelationshipTypeUseCase;
import com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.dtos.RelationshipTypeRequest;
import com.mindconnect.infrastructure.relationshiptype.adapters.in.rest.dtos.RelationshipTypeResponse;

/**
 * Adaptador de entrada REST para relationship_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/relationship-types")
public class RelationshipTypeController {

    private final RelationshipTypeUseCase useCase;

    public RelationshipTypeController(RelationshipTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<RelationshipTypeResponse> create(@Valid @RequestBody RelationshipTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(RelationshipTypeResponse.from(created));
    }

    @GetMapping
    public List<RelationshipTypeResponse> getAll() {
        return useCase.getAll().stream().map(RelationshipTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public RelationshipTypeResponse getById(@PathVariable UUID id) {
        return RelationshipTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public RelationshipTypeResponse update(@PathVariable UUID id, @Valid @RequestBody RelationshipTypeRequest request) {
        return RelationshipTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
