package com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.treatmentgoalstatus.ports.in.TreatmentGoalStatusUseCase;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos.TreatmentGoalStatusRequest;
import com.mindconnect.infrastructure.treatmentgoalstatus.adapters.in.rest.dtos.TreatmentGoalStatusResponse;

/**
 * Adaptador de entrada REST para treatment_goal_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/treatment-goal-statuses")
public class TreatmentGoalStatusController {

    private final TreatmentGoalStatusUseCase useCase;

    public TreatmentGoalStatusController(TreatmentGoalStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<TreatmentGoalStatusResponse> create(@Valid @RequestBody TreatmentGoalStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(TreatmentGoalStatusResponse.from(created));
    }

    @GetMapping
    public List<TreatmentGoalStatusResponse> getAll() {
        return useCase.getAll().stream().map(TreatmentGoalStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public TreatmentGoalStatusResponse getById(@PathVariable UUID id) {
        return TreatmentGoalStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public TreatmentGoalStatusResponse update(@PathVariable UUID id, @Valid @RequestBody TreatmentGoalStatusRequest request) {
        return TreatmentGoalStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
