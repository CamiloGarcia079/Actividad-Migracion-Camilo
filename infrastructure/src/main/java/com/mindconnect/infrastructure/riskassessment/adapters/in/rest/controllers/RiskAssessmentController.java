package com.mindconnect.infrastructure.riskassessment.adapters.in.rest.controllers;

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

import com.mindconnect.application.riskassessment.ports.in.RiskAssessmentUseCase;
import com.mindconnect.infrastructure.riskassessment.adapters.in.rest.dtos.RiskAssessmentRequest;
import com.mindconnect.infrastructure.riskassessment.adapters.in.rest.dtos.RiskAssessmentResponse;

/**
 * Adaptador de entrada REST para risk_assessments.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/risk-assessments")
public class RiskAssessmentController {

    private final RiskAssessmentUseCase useCase;

    public RiskAssessmentController(RiskAssessmentUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<RiskAssessmentResponse> create(@Valid @RequestBody RiskAssessmentRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(RiskAssessmentResponse.from(created));
    }

    @GetMapping
    public List<RiskAssessmentResponse> getAll() {
        return useCase.getAll().stream().map(RiskAssessmentResponse::from).toList();
    }

    @GetMapping("/{id}")
    public RiskAssessmentResponse getById(@PathVariable UUID id) {
        return RiskAssessmentResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public RiskAssessmentResponse update(@PathVariable UUID id, @Valid @RequestBody RiskAssessmentRequest request) {
        return RiskAssessmentResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
