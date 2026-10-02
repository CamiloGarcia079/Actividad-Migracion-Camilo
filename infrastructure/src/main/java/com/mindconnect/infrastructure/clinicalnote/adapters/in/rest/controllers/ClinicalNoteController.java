package com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.controllers;

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

import com.mindconnect.application.clinicalnote.ports.in.ClinicalNoteUseCase;
import com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.dtos.ClinicalNoteRequest;
import com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.dtos.ClinicalNoteResponse;

/**
 * Adaptador de entrada REST para clinical_notes.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/clinical-notes")
public class ClinicalNoteController {

    private final ClinicalNoteUseCase useCase;

    public ClinicalNoteController(ClinicalNoteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalNoteResponse> create(@Valid @RequestBody ClinicalNoteRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClinicalNoteResponse.from(created));
    }

    @GetMapping
    public List<ClinicalNoteResponse> getAll() {
        return useCase.getAll().stream().map(ClinicalNoteResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ClinicalNoteResponse getById(@PathVariable UUID id) {
        return ClinicalNoteResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ClinicalNoteResponse update(@PathVariable UUID id, @Valid @RequestBody ClinicalNoteRequest request) {
        return ClinicalNoteResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
