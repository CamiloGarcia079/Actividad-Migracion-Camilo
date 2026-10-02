package com.mindconnect.infrastructure.encountertype.adapters.in.rest.controllers;

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

import com.mindconnect.application.encountertype.ports.in.EncounterTypeUseCase;
import com.mindconnect.infrastructure.encountertype.adapters.in.rest.dtos.EncounterTypeRequest;
import com.mindconnect.infrastructure.encountertype.adapters.in.rest.dtos.EncounterTypeResponse;

/**
 * Adaptador de entrada REST para encounter_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/encounter-types")
public class EncounterTypeController {

    private final EncounterTypeUseCase useCase;

    public EncounterTypeController(EncounterTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EncounterTypeResponse> create(@Valid @RequestBody EncounterTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EncounterTypeResponse.from(created));
    }

    @GetMapping
    public List<EncounterTypeResponse> getAll() {
        return useCase.getAll().stream().map(EncounterTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EncounterTypeResponse getById(@PathVariable UUID id) {
        return EncounterTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EncounterTypeResponse update(@PathVariable UUID id, @Valid @RequestBody EncounterTypeRequest request) {
        return EncounterTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
