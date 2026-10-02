package com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.encounterstatus.ports.in.EncounterStatusUseCase;
import com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.dtos.EncounterStatusRequest;
import com.mindconnect.infrastructure.encounterstatus.adapters.in.rest.dtos.EncounterStatusResponse;

/**
 * Adaptador de entrada REST para encounter_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/encounter-statuses")
public class EncounterStatusController {

    private final EncounterStatusUseCase useCase;

    public EncounterStatusController(EncounterStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EncounterStatusResponse> create(@Valid @RequestBody EncounterStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EncounterStatusResponse.from(created));
    }

    @GetMapping
    public List<EncounterStatusResponse> getAll() {
        return useCase.getAll().stream().map(EncounterStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EncounterStatusResponse getById(@PathVariable UUID id) {
        return EncounterStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EncounterStatusResponse update(@PathVariable UUID id, @Valid @RequestBody EncounterStatusRequest request) {
        return EncounterStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
