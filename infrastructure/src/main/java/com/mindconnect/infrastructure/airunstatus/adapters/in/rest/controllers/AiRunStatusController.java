package com.mindconnect.infrastructure.airunstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.airunstatus.ports.in.AiRunStatusUseCase;
import com.mindconnect.infrastructure.airunstatus.adapters.in.rest.dtos.AiRunStatusRequest;
import com.mindconnect.infrastructure.airunstatus.adapters.in.rest.dtos.AiRunStatusResponse;

/**
 * Adaptador de entrada REST para ai_runs_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/ai-runs-statuses")
public class AiRunStatusController {

    private final AiRunStatusUseCase useCase;

    public AiRunStatusController(AiRunStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<AiRunStatusResponse> create(@Valid @RequestBody AiRunStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(AiRunStatusResponse.from(created));
    }

    @GetMapping
    public List<AiRunStatusResponse> getAll() {
        return useCase.getAll().stream().map(AiRunStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public AiRunStatusResponse getById(@PathVariable UUID id) {
        return AiRunStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public AiRunStatusResponse update(@PathVariable UUID id, @Valid @RequestBody AiRunStatusRequest request) {
        return AiRunStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
