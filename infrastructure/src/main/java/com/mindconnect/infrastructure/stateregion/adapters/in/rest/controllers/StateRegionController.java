package com.mindconnect.infrastructure.stateregion.adapters.in.rest.controllers;

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

import com.mindconnect.application.stateregion.ports.in.StateRegionUseCase;
import com.mindconnect.infrastructure.stateregion.adapters.in.rest.dtos.StateRegionRequest;
import com.mindconnect.infrastructure.stateregion.adapters.in.rest.dtos.StateRegionResponse;

/**
 * Adaptador de entrada REST para state_regions.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/state-regions")
public class StateRegionController {

    private final StateRegionUseCase useCase;

    public StateRegionController(StateRegionUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<StateRegionResponse> create(@Valid @RequestBody StateRegionRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(StateRegionResponse.from(created));
    }

    @GetMapping
    public List<StateRegionResponse> getAll() {
        return useCase.getAll().stream().map(StateRegionResponse::from).toList();
    }

    @GetMapping("/{id}")
    public StateRegionResponse getById(@PathVariable UUID id) {
        return StateRegionResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public StateRegionResponse update(@PathVariable UUID id, @Valid @RequestBody StateRegionRequest request) {
        return StateRegionResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
