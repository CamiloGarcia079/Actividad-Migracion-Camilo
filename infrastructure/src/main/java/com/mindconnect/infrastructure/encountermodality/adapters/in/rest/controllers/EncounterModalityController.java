package com.mindconnect.infrastructure.encountermodality.adapters.in.rest.controllers;

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

import com.mindconnect.application.encountermodality.ports.in.EncounterModalityUseCase;
import com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos.EncounterModalityRequest;
import com.mindconnect.infrastructure.encountermodality.adapters.in.rest.dtos.EncounterModalityResponse;

/**
 * Adaptador de entrada REST para encounter_modalities.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/encounter-modalities")
public class EncounterModalityController {

    private final EncounterModalityUseCase useCase;

    public EncounterModalityController(EncounterModalityUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EncounterModalityResponse> create(@Valid @RequestBody EncounterModalityRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EncounterModalityResponse.from(created));
    }

    @GetMapping
    public List<EncounterModalityResponse> getAll() {
        return useCase.getAll().stream().map(EncounterModalityResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EncounterModalityResponse getById(@PathVariable UUID id) {
        return EncounterModalityResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EncounterModalityResponse update(@PathVariable UUID id, @Valid @RequestBody EncounterModalityRequest request) {
        return EncounterModalityResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
