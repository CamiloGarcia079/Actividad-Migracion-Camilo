package com.mindconnect.infrastructure.medicationroute.adapters.in.rest.controllers;

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

import com.mindconnect.application.medicationroute.ports.in.MedicationRouteUseCase;
import com.mindconnect.infrastructure.medicationroute.adapters.in.rest.dtos.MedicationRouteRequest;
import com.mindconnect.infrastructure.medicationroute.adapters.in.rest.dtos.MedicationRouteResponse;

/**
 * Adaptador de entrada REST para medication_routes.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/medication-routes")
public class MedicationRouteController {

    private final MedicationRouteUseCase useCase;

    public MedicationRouteController(MedicationRouteUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<MedicationRouteResponse> create(@Valid @RequestBody MedicationRouteRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(MedicationRouteResponse.from(created));
    }

    @GetMapping
    public List<MedicationRouteResponse> getAll() {
        return useCase.getAll().stream().map(MedicationRouteResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MedicationRouteResponse getById(@PathVariable UUID id) {
        return MedicationRouteResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public MedicationRouteResponse update(@PathVariable UUID id, @Valid @RequestBody MedicationRouteRequest request) {
        return MedicationRouteResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
