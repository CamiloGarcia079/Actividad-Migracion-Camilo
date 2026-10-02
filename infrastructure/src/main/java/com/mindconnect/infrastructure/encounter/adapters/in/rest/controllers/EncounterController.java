package com.mindconnect.infrastructure.encounter.adapters.in.rest.controllers;

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

import com.mindconnect.application.encounter.ports.in.EncounterUseCase;
import com.mindconnect.infrastructure.encounter.adapters.in.rest.dtos.EncounterRequest;
import com.mindconnect.infrastructure.encounter.adapters.in.rest.dtos.EncounterResponse;

/**
 * Adaptador de entrada REST para encounters.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterUseCase useCase;

    public EncounterController(EncounterUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EncounterResponse> create(@Valid @RequestBody EncounterRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EncounterResponse.from(created));
    }

    @GetMapping
    public List<EncounterResponse> getAll() {
        return useCase.getAll().stream().map(EncounterResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EncounterResponse getById(@PathVariable UUID id) {
        return EncounterResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EncounterResponse update(@PathVariable UUID id, @Valid @RequestBody EncounterRequest request) {
        return EncounterResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
