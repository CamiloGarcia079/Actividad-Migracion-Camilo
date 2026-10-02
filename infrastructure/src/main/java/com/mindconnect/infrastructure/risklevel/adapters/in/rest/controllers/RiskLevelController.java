package com.mindconnect.infrastructure.risklevel.adapters.in.rest.controllers;

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

import com.mindconnect.application.risklevel.ports.in.RiskLevelUseCase;
import com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos.RiskLevelRequest;
import com.mindconnect.infrastructure.risklevel.adapters.in.rest.dtos.RiskLevelResponse;

/**
 * Adaptador de entrada REST para risk_levels.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/risk-levels")
public class RiskLevelController {

    private final RiskLevelUseCase useCase;

    public RiskLevelController(RiskLevelUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<RiskLevelResponse> create(@Valid @RequestBody RiskLevelRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(RiskLevelResponse.from(created));
    }

    @GetMapping
    public List<RiskLevelResponse> getAll() {
        return useCase.getAll().stream().map(RiskLevelResponse::from).toList();
    }

    @GetMapping("/{id}")
    public RiskLevelResponse getById(@PathVariable UUID id) {
        return RiskLevelResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public RiskLevelResponse update(@PathVariable UUID id, @Valid @RequestBody RiskLevelRequest request) {
        return RiskLevelResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
