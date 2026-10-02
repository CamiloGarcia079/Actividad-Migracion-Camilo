package com.mindconnect.infrastructure.phonecontact.adapters.in.rest.controllers;

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

import com.mindconnect.application.phonecontact.ports.in.PhoneContactUseCase;
import com.mindconnect.infrastructure.phonecontact.adapters.in.rest.dtos.PhoneContactRequest;
import com.mindconnect.infrastructure.phonecontact.adapters.in.rest.dtos.PhoneContactResponse;

/**
 * Adaptador de entrada REST para phone_contacts.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/phone-contacts")
public class PhoneContactController {

    private final PhoneContactUseCase useCase;

    public PhoneContactController(PhoneContactUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<PhoneContactResponse> create(@Valid @RequestBody PhoneContactRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(PhoneContactResponse.from(created));
    }

    @GetMapping
    public List<PhoneContactResponse> getAll() {
        return useCase.getAll().stream().map(PhoneContactResponse::from).toList();
    }

    @GetMapping("/{id}")
    public PhoneContactResponse getById(@PathVariable UUID id) {
        return PhoneContactResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public PhoneContactResponse update(@PathVariable UUID id, @Valid @RequestBody PhoneContactRequest request) {
        return PhoneContactResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
