package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.controllers;

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

import com.mindconnect.application.clinicalrecord.ports.in.ClinicalRecordUseCase;
import com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos.ClinicalRecordRequest;
import com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos.ClinicalRecordResponse;

/**
 * Adaptador de entrada REST para clinical_records.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

    private final ClinicalRecordUseCase useCase;

    public ClinicalRecordController(ClinicalRecordUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalRecordResponse> create(@Valid @RequestBody ClinicalRecordRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicalRecordResponse.from(created));
    }

    @GetMapping
    public List<ClinicalRecordResponse> getAll() {
        return useCase.getAll().stream().map(ClinicalRecordResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ClinicalRecordResponse getById(@PathVariable UUID id) {
        return ClinicalRecordResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ClinicalRecordResponse update(@PathVariable UUID id, @Valid @RequestBody ClinicalRecordRequest request) {
        return ClinicalRecordResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
