package com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.controllers;

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

import com.mindconnect.application.citymunicipality.ports.in.CityMunicipalityUseCase;
import com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.dtos.CityMunicipalityRequest;
import com.mindconnect.infrastructure.citymunicipality.adapters.in.rest.dtos.CityMunicipalityResponse;

/**
 * Adaptador de entrada REST para city_municipalities.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/city-municipalities")
public class CityMunicipalityController {

    private final CityMunicipalityUseCase useCase;

    public CityMunicipalityController(CityMunicipalityUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CityMunicipalityResponse> create(@Valid @RequestBody CityMunicipalityRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(CityMunicipalityResponse.from(created));
    }

    @GetMapping
    public List<CityMunicipalityResponse> getAll() {
        return useCase.getAll().stream().map(CityMunicipalityResponse::from).toList();
    }

    @GetMapping("/{id}")
    public CityMunicipalityResponse getById(@PathVariable UUID id) {
        return CityMunicipalityResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public CityMunicipalityResponse update(@PathVariable UUID id, @Valid @RequestBody CityMunicipalityRequest request) {
        return CityMunicipalityResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
