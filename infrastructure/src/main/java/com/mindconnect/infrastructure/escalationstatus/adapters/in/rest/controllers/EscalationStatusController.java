package com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.escalationstatus.ports.in.EscalationStatusUseCase;
import com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.dtos.EscalationStatusRequest;
import com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.dtos.EscalationStatusResponse;

/**
 * Adaptador de entrada REST para escalations_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/escalations-statuses")
public class EscalationStatusController {

    private final EscalationStatusUseCase useCase;

    public EscalationStatusController(EscalationStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EscalationStatusResponse> create(@Valid @RequestBody EscalationStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EscalationStatusResponse.from(created));
    }

    @GetMapping
    public List<EscalationStatusResponse> getAll() {
        return useCase.getAll().stream().map(EscalationStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EscalationStatusResponse getById(@PathVariable UUID id) {
        return EscalationStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EscalationStatusResponse update(@PathVariable UUID id, @Valid @RequestBody EscalationStatusRequest request) {
        return EscalationStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
