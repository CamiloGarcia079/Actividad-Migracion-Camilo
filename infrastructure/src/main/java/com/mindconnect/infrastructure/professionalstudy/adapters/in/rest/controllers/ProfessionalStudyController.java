package com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.controllers;

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

import com.mindconnect.application.professionalstudy.ports.in.ProfessionalStudyUseCase;
import com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.dtos.ProfessionalStudyRequest;
import com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.dtos.ProfessionalStudyResponse;

/**
 * Adaptador de entrada REST para professional_studies.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/professional-studies")
public class ProfessionalStudyController {

    private final ProfessionalStudyUseCase useCase;

    public ProfessionalStudyController(ProfessionalStudyUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalStudyResponse> create(@Valid @RequestBody ProfessionalStudyRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProfessionalStudyResponse.from(created));
    }

    @GetMapping
    public List<ProfessionalStudyResponse> getAll() {
        return useCase.getAll().stream().map(ProfessionalStudyResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProfessionalStudyResponse getById(@PathVariable UUID id) {
        return ProfessionalStudyResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ProfessionalStudyResponse update(@PathVariable UUID id, @Valid @RequestBody ProfessionalStudyRequest request) {
        return ProfessionalStudyResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
