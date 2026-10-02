package com.mindconnect.infrastructure.gender.adapters.in.rest.controllers;

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

import com.mindconnect.application.gender.ports.in.GenderUseCase;
import com.mindconnect.infrastructure.gender.adapters.in.rest.dtos.GenderRequest;
import com.mindconnect.infrastructure.gender.adapters.in.rest.dtos.GenderResponse;

/**
 * Adaptador de entrada REST para genders.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/genders")
public class GenderController {

    private final GenderUseCase useCase;

    public GenderController(GenderUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<GenderResponse> create(@Valid @RequestBody GenderRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(GenderResponse.from(created));
    }

    @GetMapping
    public List<GenderResponse> getAll() {
        return useCase.getAll().stream().map(GenderResponse::from).toList();
    }

    @GetMapping("/{id}")
    public GenderResponse getById(@PathVariable UUID id) {
        return GenderResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public GenderResponse update(@PathVariable UUID id, @Valid @RequestBody GenderRequest request) {
        return GenderResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
