package com.mindconnect.infrastructure.sendertype.adapters.in.rest.controllers;

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

import com.mindconnect.application.sendertype.ports.in.SenderTypeUseCase;
import com.mindconnect.infrastructure.sendertype.adapters.in.rest.dtos.SenderTypeRequest;
import com.mindconnect.infrastructure.sendertype.adapters.in.rest.dtos.SenderTypeResponse;

/**
 * Adaptador de entrada REST para sender_types.
 * Solo traduce HTTP a casos de uso: no tiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/sender-types")
public class SenderTypeController {

    private final SenderTypeUseCase useCase;

    public SenderTypeController(SenderTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<SenderTypeResponse> create(@Valid @RequestBody SenderTypeRequest request) {
        var created = useCase.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(SenderTypeResponse.from(created));
    }

    @GetMapping
    public List<SenderTypeResponse> getAll() {
        return useCase.getAll().stream().map(SenderTypeResponse::from).toList();
    }

    @GetMapping("/{id}")
    public SenderTypeResponse getById(@PathVariable UUID id) {
        return SenderTypeResponse.from(useCase.getById(id));
    }

    @PutMapping("/{id}")
    public SenderTypeResponse update(@PathVariable UUID id, @Valid @RequestBody SenderTypeRequest request) {
        return SenderTypeResponse.from(useCase.update(id, request.toCommand()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
