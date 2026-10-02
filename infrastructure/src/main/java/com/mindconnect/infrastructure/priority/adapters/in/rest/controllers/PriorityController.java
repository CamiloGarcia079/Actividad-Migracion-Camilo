package com.mindconnect.infrastructure.priority.adapters.in.rest.controllers;

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

import com.mindconnect.application.priority.ports.in.PriorityUseCase;
import com.mindconnect.infrastructure.priority.adapters.in.rest.dtos.PriorityRequest;
import com.mindconnect.infrastructure.priority.adapters.in.rest.dtos.PriorityResponse;

/**
 * Adaptador de entrada REST para priorities.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

    private final PriorityUseCase useCase;

    public PriorityController(PriorityUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PriorityResponse> create(@Valid @RequestBody PriorityRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PriorityResponse.from(created));
    }

    @GetMapping
    public List<PriorityResponse> getAll() {
        return useCase.getAll().stream().map(PriorityResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PriorityResponse getById(@PathVariable UUID id) {
        return PriorityResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public PriorityResponse update(@PathVariable UUID id, @Valid @RequestBody PriorityRequest request) {
        return PriorityResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
