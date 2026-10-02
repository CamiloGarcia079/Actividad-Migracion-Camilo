package com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.controllers;

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

import com.mindconnect.application.clinicalrecordstatus.ports.in.ClinicalRecordStatusUseCase;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos.ClinicalRecordStatusRequest;
import com.mindconnect.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos.ClinicalRecordStatusResponse;

/**
 * Adaptador de entrada REST para clinical_record_statuses.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/clinical-record-statuses")
public class ClinicalRecordStatusController {

    private final ClinicalRecordStatusUseCase useCase;

    public ClinicalRecordStatusController(ClinicalRecordStatusUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalRecordStatusResponse> create(@Valid @RequestBody ClinicalRecordStatusRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicalRecordStatusResponse.from(created));
    }

    @GetMapping
    public List<ClinicalRecordStatusResponse> getAll() {
        return useCase.getAll().stream().map(ClinicalRecordStatusResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ClinicalRecordStatusResponse getById(@PathVariable UUID id) {
        return ClinicalRecordStatusResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ClinicalRecordStatusResponse update(@PathVariable UUID id, @Valid @RequestBody ClinicalRecordStatusRequest request) {
        return ClinicalRecordStatusResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
