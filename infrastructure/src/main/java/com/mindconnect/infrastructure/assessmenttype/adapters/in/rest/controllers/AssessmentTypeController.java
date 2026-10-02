package com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.controllers;

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

import com.mindconnect.application.assessmenttype.ports.in.AssessmentTypeUseCase;
import com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.dtos.AssessmentTypeRequest;
import com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.dtos.AssessmentTypeResponse;

/**
 * Adaptador de entrada REST para assessment_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/assessment-types")
public class AssessmentTypeController {

    private final AssessmentTypeUseCase useCase;

    public AssessmentTypeController(AssessmentTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<AssessmentTypeResponse> create(@Valid @RequestBody AssessmentTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(AssessmentTypeResponse.from(created));
    }

    @GetMapping
    public List<AssessmentTypeResponse> getAll() {
        return useCase.getAll().stream().map(AssessmentTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public AssessmentTypeResponse getById(@PathVariable UUID id) {
        return AssessmentTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public AssessmentTypeResponse update(@PathVariable UUID id, @Valid @RequestBody AssessmentTypeRequest request) {
        return AssessmentTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
