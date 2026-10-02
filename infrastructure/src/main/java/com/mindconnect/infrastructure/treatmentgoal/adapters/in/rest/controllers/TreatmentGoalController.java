package com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.controllers;

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

import com.mindconnect.application.treatmentgoal.ports.in.TreatmentGoalUseCase;
import com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.dtos.TreatmentGoalRequest;
import com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.dtos.TreatmentGoalResponse;

/**
 * Adaptador de entrada REST para treatment_goals.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/treatment-goals")
public class TreatmentGoalController {

    private final TreatmentGoalUseCase useCase;

    public TreatmentGoalController(TreatmentGoalUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentGoalResponse> create(@Valid @RequestBody TreatmentGoalRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(TreatmentGoalResponse.from(created));
    }

    @GetMapping
    public List<TreatmentGoalResponse> getAll() {
        return useCase.getAll().stream().map(TreatmentGoalResponse::from).toList();
    }

    @GetMapping("/{id}")
    public TreatmentGoalResponse getById(@PathVariable UUID id) {
        return TreatmentGoalResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public TreatmentGoalResponse update(@PathVariable UUID id, @Valid @RequestBody TreatmentGoalRequest request) {
        return TreatmentGoalResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
