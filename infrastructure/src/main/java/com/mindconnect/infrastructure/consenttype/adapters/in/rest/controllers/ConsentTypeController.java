package com.mindconnect.infrastructure.consenttype.adapters.in.rest.controllers;

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

import com.mindconnect.application.consenttype.ports.in.ConsentTypeUseCase;
import com.mindconnect.infrastructure.consenttype.adapters.in.rest.dtos.ConsentTypeRequest;
import com.mindconnect.infrastructure.consenttype.adapters.in.rest.dtos.ConsentTypeResponse;

/**
 * Adaptador de entrada REST para consent_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/consent-types")
public class ConsentTypeController {

    private final ConsentTypeUseCase useCase;

    public ConsentTypeController(ConsentTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ConsentTypeResponse> create(@Valid @RequestBody ConsentTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ConsentTypeResponse.from(created));
    }

    @GetMapping
    public List<ConsentTypeResponse> getAll() {
        return useCase.getAll().stream().map(ConsentTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ConsentTypeResponse getById(@PathVariable UUID id) {
        return ConsentTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ConsentTypeResponse update(@PathVariable UUID id, @Valid @RequestBody ConsentTypeRequest request) {
        return ConsentTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
