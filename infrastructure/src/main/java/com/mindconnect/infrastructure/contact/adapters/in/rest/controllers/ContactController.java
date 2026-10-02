package com.mindconnect.infrastructure.contact.adapters.in.rest.controllers;

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

import com.mindconnect.application.contact.ports.in.ContactUseCase;
import com.mindconnect.infrastructure.contact.adapters.in.rest.dtos.ContactRequest;
import com.mindconnect.infrastructure.contact.adapters.in.rest.dtos.ContactResponse;

/**
 * Adaptador de entrada REST para contacts.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactUseCase useCase;

    public ContactController(ContactUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> create(@Valid @RequestBody ContactRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactResponse.from(created));
    }

    @GetMapping
    public List<ContactResponse> getAll() {
        return useCase.getAll().stream().map(ContactResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ContactResponse getById(@PathVariable UUID id) {
        return ContactResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public ContactResponse update(@PathVariable UUID id, @Valid @RequestBody ContactRequest request) {
        return ContactResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
