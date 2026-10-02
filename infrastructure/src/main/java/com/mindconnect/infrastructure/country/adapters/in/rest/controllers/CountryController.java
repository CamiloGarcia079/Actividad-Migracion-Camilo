package com.mindconnect.infrastructure.country.adapters.in.rest.controllers;

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

import com.mindconnect.application.country.ports.in.CountryUseCase;
import com.mindconnect.infrastructure.country.adapters.in.rest.dtos.CountryRequest;
import com.mindconnect.infrastructure.country.adapters.in.rest.dtos.CountryResponse;

/**
 * Adaptador de entrada REST para countries.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryUseCase useCase;

    public CountryController(CountryUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@Valid @RequestBody CountryRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(CountryResponse.from(created));
    }

    @GetMapping
    public List<CountryResponse> getAll() {
        return useCase.getAll().stream().map(CountryResponse::from).toList();
    }

    @GetMapping("/{id}")
    public CountryResponse getById(@PathVariable UUID id) {
        return CountryResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public CountryResponse update(@PathVariable UUID id, @Valid @RequestBody CountryRequest request) {
        return CountryResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
