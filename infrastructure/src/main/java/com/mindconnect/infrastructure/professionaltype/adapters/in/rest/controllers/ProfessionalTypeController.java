package com.mindconnect.infrastructure.professionaltype.adapters.in.rest.controllers;

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

import com.mindconnect.application.professionaltype.ports.in.ProfessionalTypeUseCase;
import com.mindconnect.infrastructure.professionaltype.adapters.in.rest.dtos.ProfessionalTypeRequest;
import com.mindconnect.infrastructure.professionaltype.adapters.in.rest.dtos.ProfessionalTypeResponse;

/**
 * Adaptador de entrada REST para professional_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/professional-types")
public class ProfessionalTypeController {

    private final ProfessionalTypeUseCase useCase;

    public ProfessionalTypeController(ProfessionalTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalTypeResponse> create(@Valid @RequestBody ProfessionalTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfessionalTypeResponse.from(created));
    }

    @GetMapping
    public List<ProfessionalTypeResponse> getAll() {
        return useCase.getAll().stream().map(ProfessionalTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProfessionalTypeResponse getById(@PathVariable UUID id) {
        return ProfessionalTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ProfessionalTypeResponse update(@PathVariable UUID id, @Valid @RequestBody ProfessionalTypeRequest request) {
        return ProfessionalTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
