package com.mindconnect.infrastructure.aimodel.adapters.in.rest.controllers;

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

import com.mindconnect.application.aimodel.ports.in.AiModelUseCase;
import com.mindconnect.infrastructure.aimodel.adapters.in.rest.dtos.AiModelRequest;
import com.mindconnect.infrastructure.aimodel.adapters.in.rest.dtos.AiModelResponse;

/**
 * Adaptador de entrada REST para ai_models.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/ai-models")
public class AiModelController {

    private final AiModelUseCase useCase;

    public AiModelController(AiModelUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<AiModelResponse> create(@Valid @RequestBody AiModelRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(AiModelResponse.from(created));
    }

    @GetMapping
    public List<AiModelResponse> getAll() {
        return useCase.getAll().stream().map(AiModelResponse::from).toList();
    }

    @GetMapping("/{id}")
    public AiModelResponse getById(@PathVariable UUID id) {
        return AiModelResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public AiModelResponse update(@PathVariable UUID id, @Valid @RequestBody AiModelRequest request) {
        return AiModelResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
