package com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.controllers;

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

import com.mindconnect.application.treatmentplan.ports.in.TreatmentPlanUseCase;
import com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.dtos.TreatmentPlanRequest;
import com.mindconnect.infrastructure.treatmentplan.adapters.in.rest.dtos.TreatmentPlanResponse;

/**
 * Adaptador de entrada REST para treatment_plans.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/treatment-plans")
public class TreatmentPlanController {

    private final TreatmentPlanUseCase useCase;

    public TreatmentPlanController(TreatmentPlanUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentPlanResponse> create(@Valid @RequestBody TreatmentPlanRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(TreatmentPlanResponse.from(created));
    }

    @GetMapping
    public List<TreatmentPlanResponse> getAll() {
        return useCase.getAll().stream().map(TreatmentPlanResponse::from).toList();
    }

    @GetMapping("/{id}")
    public TreatmentPlanResponse getById(@PathVariable UUID id) {
        return TreatmentPlanResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public TreatmentPlanResponse update(@PathVariable UUID id, @Valid @RequestBody TreatmentPlanRequest request) {
        return TreatmentPlanResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
