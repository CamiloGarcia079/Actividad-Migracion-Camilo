package com.mindconnect.infrastructure.patientallergy.adapters.in.rest.controllers;

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

import com.mindconnect.application.patientallergy.ports.in.PatientAllergyUseCase;
import com.mindconnect.infrastructure.patientallergy.adapters.in.rest.dtos.PatientAllergyRequest;
import com.mindconnect.infrastructure.patientallergy.adapters.in.rest.dtos.PatientAllergyResponse;

/**
 * Adaptador de entrada REST para patient_allergies.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/patient-allergies")
public class PatientAllergyController {

    private final PatientAllergyUseCase useCase;

    public PatientAllergyController(PatientAllergyUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PatientAllergyResponse> create(@Valid @RequestBody PatientAllergyRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PatientAllergyResponse.from(created));
    }

    @GetMapping
    public List<PatientAllergyResponse> getAll() {
        return useCase.getAll().stream().map(PatientAllergyResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PatientAllergyResponse getById(@PathVariable UUID id) {
        return PatientAllergyResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public PatientAllergyResponse update(@PathVariable UUID id, @Valid @RequestBody PatientAllergyRequest request) {
        return PatientAllergyResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
