package com.mindconnect.infrastructure.patientcontact.adapters.in.rest.controllers;

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

import com.mindconnect.application.patientcontact.ports.in.PatientContactUseCase;
import com.mindconnect.infrastructure.patientcontact.adapters.in.rest.dtos.PatientContactRequest;
import com.mindconnect.infrastructure.patientcontact.adapters.in.rest.dtos.PatientContactResponse;

/**
 * Adaptador de entrada REST para patient_contacts.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/patient-contacts")
public class PatientContactController {

    private final PatientContactUseCase useCase;

    public PatientContactController(PatientContactUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PatientContactResponse> create(@Valid @RequestBody PatientContactRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PatientContactResponse.from(created));
    }

    @GetMapping
    public List<PatientContactResponse> getAll() {
        return useCase.getAll().stream().map(PatientContactResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PatientContactResponse getById(@PathVariable UUID id) {
        return PatientContactResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public PatientContactResponse update(@PathVariable UUID id, @Valid @RequestBody PatientContactRequest request) {
        return PatientContactResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
