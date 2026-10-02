package com.mindconnect.infrastructure.professional.adapters.in.rest.controllers;

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

import com.mindconnect.application.professional.ports.in.ProfessionalUseCase;
import com.mindconnect.infrastructure.professional.adapters.in.rest.dtos.ProfessionalRequest;
import com.mindconnect.infrastructure.professional.adapters.in.rest.dtos.ProfessionalResponse;

/**
 * Adaptador de entrada REST para professionals.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final ProfessionalUseCase useCase;

    public ProfessionalController(ProfessionalUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponse> create(@Valid @RequestBody ProfessionalRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfessionalResponse.from(created));
    }

    @GetMapping
    public List<ProfessionalResponse> getAll() {
        return useCase.getAll().stream().map(ProfessionalResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProfessionalResponse getById(@PathVariable UUID id) {
        return ProfessionalResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ProfessionalResponse update(@PathVariable UUID id, @Valid @RequestBody ProfessionalRequest request) {
        return ProfessionalResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
