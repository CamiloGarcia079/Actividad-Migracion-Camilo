package com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.treatmentstatus.ports.in.TreatmentStatusUseCase;
import com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.dtos.TreatmentStatusRequest;
import com.mindconnect.infrastructure.treatmentstatus.adapters.in.rest.dtos.TreatmentStatusResponse;

/**
 * Adaptador de entrada REST para treatment_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/treatment-statuses")
public class TreatmentStatusController {

    private final TreatmentStatusUseCase useCase;

    public TreatmentStatusController(TreatmentStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentStatusResponse> create(@Valid @RequestBody TreatmentStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(TreatmentStatusResponse.from(created));
    }

    @GetMapping
    public List<TreatmentStatusResponse> getAll() {
        return useCase.getAll().stream().map(TreatmentStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public TreatmentStatusResponse getById(@PathVariable UUID id) {
        return TreatmentStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public TreatmentStatusResponse update(@PathVariable UUID id, @Valid @RequestBody TreatmentStatusRequest request) {
        return TreatmentStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
