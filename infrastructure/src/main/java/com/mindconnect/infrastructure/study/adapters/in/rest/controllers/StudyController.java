package com.mindconnect.infrastructure.study.adapters.in.rest.controllers;

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

import com.mindconnect.application.study.ports.in.StudyUseCase;
import com.mindconnect.infrastructure.study.adapters.in.rest.dtos.StudyRequest;
import com.mindconnect.infrastructure.study.adapters.in.rest.dtos.StudyResponse;

/**
 * Adaptador de entrada REST para studies.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/studies")
public class StudyController {

    private final StudyUseCase useCase;

    public StudyController(StudyUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<StudyResponse> create(@Valid @RequestBody StudyRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(StudyResponse.from(created));
    }

    @GetMapping
    public List<StudyResponse> getAll() {
        return useCase.getAll().stream().map(StudyResponse::from).toList();
    }

    @GetMapping("/{id}")
    public StudyResponse getById(@PathVariable UUID id) {
        return StudyResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public StudyResponse update(@PathVariable UUID id, @Valid @RequestBody StudyRequest request) {
        return StudyResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
