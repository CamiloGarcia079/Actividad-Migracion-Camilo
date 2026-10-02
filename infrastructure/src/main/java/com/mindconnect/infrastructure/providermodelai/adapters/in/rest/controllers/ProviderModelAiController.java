package com.mindconnect.infrastructure.providermodelai.adapters.in.rest.controllers;

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

import com.mindconnect.application.providermodelai.ports.in.ProviderModelAiUseCase;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos.ProviderModelAiRequest;
import com.mindconnect.infrastructure.providermodelai.adapters.in.rest.dtos.ProviderModelAiResponse;

/**
 * Adaptador de entrada REST para provider_models_ai.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/provider-models-ai")
public class ProviderModelAiController {

    private final ProviderModelAiUseCase useCase;

    public ProviderModelAiController(ProviderModelAiUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ProviderModelAiResponse> create(@Valid @RequestBody ProviderModelAiRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProviderModelAiResponse.from(created));
    }

    @GetMapping
    public List<ProviderModelAiResponse> getAll() {
        return useCase.getAll().stream().map(ProviderModelAiResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProviderModelAiResponse getById(@PathVariable UUID id) {
        return ProviderModelAiResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ProviderModelAiResponse update(@PathVariable UUID id, @Valid @RequestBody ProviderModelAiRequest request) {
        return ProviderModelAiResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
