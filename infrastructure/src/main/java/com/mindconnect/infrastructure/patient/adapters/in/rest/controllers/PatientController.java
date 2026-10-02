package com.mindconnect.infrastructure.patient.adapters.in.rest.controllers;

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

import com.mindconnect.application.patient.ports.in.PatientUseCase;
import com.mindconnect.infrastructure.patient.adapters.in.rest.dtos.PatientRequest;
import com.mindconnect.infrastructure.patient.adapters.in.rest.dtos.PatientResponse;

/**
 * Adaptador de entrada REST para patients.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientUseCase useCase;

    public PatientController(PatientUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody PatientRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PatientResponse.from(created));
    }

    @GetMapping
    public List<PatientResponse> getAll() {
        return useCase.getAll().stream().map(PatientResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PatientResponse getById(@PathVariable UUID id) {
        return PatientResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable UUID id, @Valid @RequestBody PatientRequest request) {
        return PatientResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
