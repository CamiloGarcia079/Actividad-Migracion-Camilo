package com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.controllers;

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

import com.mindconnect.application.diagnosticsystem.ports.in.DiagnosticSystemUseCase;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.dtos.DiagnosticSystemRequest;
import com.mindconnect.infrastructure.diagnosticsystem.adapters.in.rest.dtos.DiagnosticSystemResponse;

/**
 * Adaptador de entrada REST para diagnostic_systems.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/diagnostic-systems")
public class DiagnosticSystemController {

    private final DiagnosticSystemUseCase useCase;

    public DiagnosticSystemController(DiagnosticSystemUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<DiagnosticSystemResponse> create(@Valid @RequestBody DiagnosticSystemRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(DiagnosticSystemResponse.from(created));
    }

    @GetMapping
    public List<DiagnosticSystemResponse> getAll() {
        return useCase.getAll().stream().map(DiagnosticSystemResponse::from).toList();
    }

    @GetMapping("/{id}")
    public DiagnosticSystemResponse getById(@PathVariable UUID id) {
        return DiagnosticSystemResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public DiagnosticSystemResponse update(@PathVariable UUID id, @Valid @RequestBody DiagnosticSystemRequest request) {
        return DiagnosticSystemResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
