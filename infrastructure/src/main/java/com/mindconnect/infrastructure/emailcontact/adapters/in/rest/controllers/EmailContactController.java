package com.mindconnect.infrastructure.emailcontact.adapters.in.rest.controllers;

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

import com.mindconnect.application.emailcontact.ports.in.EmailContactUseCase;
import com.mindconnect.infrastructure.emailcontact.adapters.in.rest.dtos.EmailContactRequest;
import com.mindconnect.infrastructure.emailcontact.adapters.in.rest.dtos.EmailContactResponse;

/**
 * Adaptador de entrada REST para email_contacts.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/email-contacts")
public class EmailContactController {

    private final EmailContactUseCase useCase;

    public EmailContactController(EmailContactUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EmailContactResponse> create(@Valid @RequestBody EmailContactRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(EmailContactResponse.from(created));
    }

    @GetMapping
    public List<EmailContactResponse> getAll() {
        return useCase.getAll().stream().map(EmailContactResponse::from).toList();
    }

    @GetMapping("/{id}")
    public EmailContactResponse getById(@PathVariable UUID id) {
        return EmailContactResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public EmailContactResponse update(@PathVariable UUID id, @Valid @RequestBody EmailContactRequest request) {
        return EmailContactResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
